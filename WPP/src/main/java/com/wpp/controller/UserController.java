package com.wpp.controller;

import java.io.File;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import com.wpp.domain.Message;
import com.wpp.domain.User;
import com.wpp.security.Role;
import com.wpp.service.UserService;


@Controller
@RequestMapping("/users")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	//프로필 이미지 업로드 경로(applicationcontext.xml에 경로 있음)
	@Resource(name="uploadPath2")
	String uploadPath2;

	@Autowired
	UserService userService;
	//시큐리티 암호화 관련 
	@Autowired
	private PasswordEncoder passwordEncoder;
	//메일 관련
	@Autowired
	private JavaMailSender mailSender;
	
	//회원가입
	@RequestMapping("/form")
	public String createform(Model model){
		model.addAttribute("user", new User());
		return "users/form";
	}
	
	//회원 생성
	@RequestMapping(value="/create" , method=RequestMethod.POST)
	public String create(@Valid User user, BindingResult bindingResult){
		log.debug("User : {}", user);
		if(bindingResult.hasErrors()){
			
			log.debug("Binding Result has error");
			List<ObjectError> errors = bindingResult.getAllErrors();
			for(ObjectError error: errors){
				log.debug("error: {}", error.getDefaultMessage());
			}
			return "users/form";
		}		
		userService.create(user);
		log.debug("Database: {}", userService.findByID(user.getUserId()));
		return "users/joinok";
	}
	
	
	//내정보- 세션으로 아이디 가져와서 정보 불러오기 
	@RequestMapping("/myinfo")
	public String myifo(Model model, HttpSession session){
		if(session ==null){
			throw new IllegalArgumentException("사용자 아이디가 필요합니다.");
		}
		
		String userId = (String) session.getAttribute("userId");
		User user = userService.findByID(userId);		
		model.addAttribute("user", user);
		return "users/myinfo";
	}
	
	//정보 수정창으로 이동
	@RequestMapping("/myinfo/infomodify")
	public String infomodify(Model model, HttpSession session){
		if(session ==null){
			throw new IllegalArgumentException("사용자 아이디가 필요합니다.");
		}
		
		String userId = (String) session.getAttribute("userId");
		User user = userService.findByID(userId);		
		model.addAttribute("user", user);
		return "users/infomodify";
	}
		
	//정보 수정
	@RequestMapping("/modify")
	public String modify(Model model, HttpSession session,User user){
		if(session ==null){
			throw new IllegalArgumentException("사용자 아이디가 필요합니다.");
		}
		
		String userId = (String) session.getAttribute("userId");
		userService.update(user);		
		
		return "users/myinfo";
	}
	
	//로그아웃
	@RequestMapping("/logout")		  
  	public String logout(HttpSession session){		  	 		
 		session.invalidate();		 		
 		return "redirect:/";		 	
 	}
	
	//프로필 변경
		@RequestMapping(value="imgmodify", method=RequestMethod.POST)
		public ModelAndView imgmodify(@ModelAttribute User vo, HttpSession session, MultipartFile file, ModelAndView mav) throws Exception{
			String userId = (String) session.getAttribute("userId");
		    //업로드 파일 이름중복 방지 
		    UUID uuid = UUID.randomUUID();	    
	        String savedName = uuid.toString()+"_"+file.getOriginalFilename();
	        File target = new File(uploadPath2, savedName);
	        
	        // 임시디렉토리에 저장된 업로드된 파일을 지정된 디렉토리로 복사
	        // FileCopyUtils.copy(바이트배열, 파일객체)
	        FileCopyUtils.copy(file.getBytes(), target);

	   
	        mav.addObject("savedName", savedName);
	        //vo에 데이터 넣기
	        
	        vo.setProfileimg(savedName);
	        vo.setUserId(userId);
	     
	        userService.imgmodify(vo);
	        session.setAttribute("userimg", savedName);
	        mav.setViewName("redirect:/");
	        return mav;

		}
	
	//아이디 중복체크 
	 @RequestMapping(value = "/checkId.do")
	 public void checkId(HttpServletRequest req, HttpServletResponse res,
	   ModelMap model) throws Exception {
	  PrintWriter out = res.getWriter();
	  try {

	   // 넘어온 ID를 받는다.
	   String paramId = (req.getParameter("prmId") == null) ? "" : String.valueOf(req.getParameter("prmId"));

	   User vo = new User();
	   vo.setUserId(paramId.trim());
	   int chkPoint = userService.checkId(vo);

	   out.print(chkPoint);
	   out.flush();
	   out.close();
	  } catch (Exception e) {
	   e.printStackTrace();
	   out.print("1");
	  }
	 }
	 
	 
//security로그인	 
	//실패했을때?? 
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public ModelAndView signin(@RequestParam(value = "error", required = false) String error, Model model) {
		//model.addAttribute("error", error);
		
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("error", error);
		mav.setViewName("redirect:/");
		return mav;
	}

	//성공했을때 
	@PreAuthorize("authenticated")
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String mypage(Model model,HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			
		//유저 프로필 가져와서 세션에 적용하기 
		String userId = auth.getName(); 
		String userimg = userService.findprofile(userId);	
		session.setAttribute("userimg", userimg);
		session.setAttribute("userId", auth.getName()); 
		return "redirect:/";
		}
	 
	 //idpw찾기창으로 이동
	 @RequestMapping("/idpwfind")
	public String idfind(){
		return "/users/findidpw";
	}
	 	
	//아이디 찾기
	@RequestMapping("/findid")
	public ModelAndView findid(@RequestParam String user_email, @RequestParam String user_name){
		try{
		//id찾기시 **~~형태로 주기 위해
		String userId = userService.finduserId(user_email, user_name);
		
		String Id1 = userId.substring(0,2);
		Id1 = "**";
		String Id2 = userId.substring(2);
		
		userId = Id1 + Id2;
			
		ModelAndView mav = new ModelAndView();
		mav.addObject("userId",userId);
		mav.setViewName("users/findresult");
		return mav;
		}catch (Exception e){
			ModelAndView mav = new ModelAndView();
			mav.setViewName("users/finderror");
			return mav;
		}
	}
		
		//비밀번호 찾기  
		@RequestMapping("/findpw")
		public ModelAndView findpw(@RequestParam String user_email,@RequestParam String user_id ){
			try{			
			User user = new User();
			user = userService.findByID(user_id);
			
			//입력한 아이디에 등록된 이메일과  입력한 이메일이 맞지 않으면 오류!
			String useremail = userService.finduseremail(user_id); 
			if(!useremail.equals(user_email)){
				ModelAndView mav = new ModelAndView();
				mav.setViewName("users/findemailerror");
				return mav;
			}
			
			Random rd = new Random();
	 		int num = rd.nextInt(10000)+1000; //랜덤 숫자 범위 정하기 (10)이 0~9니깐... 10000이면 0~9999 여기에 천을더해주면 1000~10999(
			String pw = "cot"+Integer.toString(num) +"cot"; //초기화된 비밀번호 형태 cot 숫자 cot 
			
			String password = pw; 
			
			//비밀 번호 초기화 
			user.setPassword(password);
			userService.update(user);
			
			//이메일 보내기 
			String setfrom = "Cot.com";         //보내는 이메일 이름?
			String tomail  = user_email;     // 받는 사람 이메일
			String title   = "Cot 비밀번호 찾기";      // 제목
			String content = "비밀번호는 다음과 같이 초기화됩니다.\n"+"비밀번호는"+password+"입니다.";
			
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper  = new MimeMessageHelper(message, true, "UTF-8");
			 
			messageHelper.setFrom(setfrom);  // 보내는사람 생략하거나 하면 정상작동을 안함
			messageHelper.setTo(tomail);     // 받는사람 이메일
			messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
			messageHelper.setText(content);  // 메일 내용
			     
			mailSender.send(message);
	
			ModelAndView mav = new ModelAndView();	
			mav.setViewName("users/sendresult");
			return mav;
			
			}catch (Exception e){
				ModelAndView mav = new ModelAndView();
				mav.setViewName("users/finderror");
				return mav;
			}
		}
		
		//메시지 폼 띄우기
		@RequestMapping(value = "/formmessage", method = RequestMethod.GET)
		public ModelAndView formmessage(@RequestParam String writer,  HttpSession session) throws UnsupportedEncodingException {
			//보내는 사람
			String senduser = (String)session.getAttribute("userId");
			//받는 사람 
			writer  = new String(writer.getBytes("8859_1"),"UTF-8"); //get방식에서 한글을 넣으면 깨지므로  바꿔주기!
			String receiver = writer;
				
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("senduser", senduser);
			mav.addObject("receiver", receiver);
			
			
			mav.setViewName("/users/messageform");
			return mav;
		}
	
		//메세지 보내기 	
		@RequestMapping(value = "/sendmessage", method = RequestMethod.POST)
		public ModelAndView usermessage(@RequestParam String senduser, @RequestParam String content,@RequestParam String receiver,  HttpSession session) {
			
			Message message = new Message();
			message.setReceiver(receiver);
			message.setSenduser(senduser);
			message.setContent(content);
					
			userService.sendmessage(message);
					
			ModelAndView mav = new ModelAndView();	
			mav.setViewName("/users/messageok");
			return mav;
		}
		
		//메세지보기 
		@RequestMapping(value = "/message", method = RequestMethod.GET)
		public ModelAndView viewmessage(HttpSession session) {
			
			String userid = (String)session.getAttribute("userId");
			List<Message> message = userService.viewmessage(userid);
					
			ModelAndView mav = new ModelAndView();
			mav.addObject("message", message);
			mav.setViewName("/users/message");
			return mav;
		}
		
		//보낸메세지보기 
		@RequestMapping(value = "/viewsendmessage", method = RequestMethod.GET)
		public ModelAndView viewsendmessage(HttpSession session) {
			
			String userid = (String)session.getAttribute("userId");
			List<Message> message = userService.viewsendmessage(userid);
					
			ModelAndView mav = new ModelAndView();
			mav.addObject("message", message);
			mav.setViewName("/users/viewsendmessage");
			return mav;
		}

		
		
		//탈퇴창 이동
		@RequestMapping(value = "/unregisterform")
		public ModelAndView unregisterform(HttpSession session) {
					
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/users/unregisterform");
			return mav;
		}
		
		
		//회원 탈퇴
		@RequestMapping(value = "/unregister", method = RequestMethod.POST)
		public ModelAndView userunregister(HttpSession session, @RequestParam String user_password) {
			String userid = (String)session.getAttribute("userId");
			User user = new User();
			
			user = userService.findByID(userid);
			//비밀번호 비교 후 탈퇴
			if(passwordEncoder.matches(user_password ,user.getPassword())){
				userService.unregister(userid);
				
				ModelAndView mav = new ModelAndView();
				mav.setViewName("/users/unregisterok");
				
				session.removeAttribute("userId");
				return mav;
			}else{
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/users/unregisterfail");
			return mav;
			}
		}
		
		
		
}