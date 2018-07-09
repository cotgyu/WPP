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
	//������ �̹��� ���ε� ���(applicationcontext.xml�� ��� ����)
	@Resource(name="uploadPath2")
	String uploadPath2;

	@Autowired
	UserService userService;
	//��ť��Ƽ ��ȣȭ ���� 
	@Autowired
	private PasswordEncoder passwordEncoder;
	//���� ����
	@Autowired
	private JavaMailSender mailSender;
	
	//ȸ������
	@RequestMapping("/form")
	public String createform(Model model){
		model.addAttribute("user", new User());
		return "users/form";
	}
	
	//ȸ�� ����
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
	
	
	//������- �������� ���̵� �����ͼ� ���� �ҷ����� 
	@RequestMapping("/myinfo")
	public String myifo(Model model, HttpSession session){
		if(session ==null){
			throw new IllegalArgumentException("����� ���̵� �ʿ��մϴ�.");
		}
		
		String userId = (String) session.getAttribute("userId");
		User user = userService.findByID(userId);		
		model.addAttribute("user", user);
		return "users/myinfo";
	}
	
	//���� ����â���� �̵�
	@RequestMapping("/myinfo/infomodify")
	public String infomodify(Model model, HttpSession session){
		if(session ==null){
			throw new IllegalArgumentException("����� ���̵� �ʿ��մϴ�.");
		}
		
		String userId = (String) session.getAttribute("userId");
		User user = userService.findByID(userId);		
		model.addAttribute("user", user);
		return "users/infomodify";
	}
		
	//���� ����
	@RequestMapping("/modify")
	public String modify(Model model, HttpSession session,User user){
		if(session ==null){
			throw new IllegalArgumentException("����� ���̵� �ʿ��մϴ�.");
		}
		
		String userId = (String) session.getAttribute("userId");
		userService.update(user);		
		
		return "users/myinfo";
	}
	
	//�α׾ƿ�
	@RequestMapping("/logout")		  
  	public String logout(HttpSession session){		  	 		
 		session.invalidate();		 		
 		return "redirect:/";		 	
 	}
	
	//������ ����
		@RequestMapping(value="imgmodify", method=RequestMethod.POST)
		public ModelAndView imgmodify(@ModelAttribute User vo, HttpSession session, MultipartFile file, ModelAndView mav) throws Exception{
			String userId = (String) session.getAttribute("userId");
		    //���ε� ���� �̸��ߺ� ���� 
		    UUID uuid = UUID.randomUUID();	    
	        String savedName = uuid.toString()+"_"+file.getOriginalFilename();
	        File target = new File(uploadPath2, savedName);
	        
	        // �ӽõ��丮�� ����� ���ε�� ������ ������ ���丮�� ����
	        // FileCopyUtils.copy(����Ʈ�迭, ���ϰ�ü)
	        FileCopyUtils.copy(file.getBytes(), target);

	   
	        mav.addObject("savedName", savedName);
	        //vo�� ������ �ֱ�
	        
	        vo.setProfileimg(savedName);
	        vo.setUserId(userId);
	     
	        userService.imgmodify(vo);
	        session.setAttribute("userimg", savedName);
	        mav.setViewName("redirect:/");
	        return mav;

		}
	
	//���̵� �ߺ�üũ 
	 @RequestMapping(value = "/checkId.do")
	 public void checkId(HttpServletRequest req, HttpServletResponse res,
	   ModelMap model) throws Exception {
	  PrintWriter out = res.getWriter();
	  try {

	   // �Ѿ�� ID�� �޴´�.
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
	 
	 
//security�α���	 
	//����������?? 
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public ModelAndView signin(@RequestParam(value = "error", required = false) String error, Model model) {
		//model.addAttribute("error", error);
		
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("error", error);
		mav.setViewName("redirect:/");
		return mav;
	}

	//���������� 
	@PreAuthorize("authenticated")
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String mypage(Model model,HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			
		//���� ������ �����ͼ� ���ǿ� �����ϱ� 
		String userId = auth.getName(); 
		String userimg = userService.findprofile(userId);	
		session.setAttribute("userimg", userimg);
		session.setAttribute("userId", auth.getName()); 
		return "redirect:/";
		}
	 
	 //idpwã��â���� �̵�
	 @RequestMapping("/idpwfind")
	public String idfind(){
		return "/users/findidpw";
	}
	 	
	//���̵� ã��
	@RequestMapping("/findid")
	public ModelAndView findid(@RequestParam String user_email, @RequestParam String user_name){
		try{
		//idã��� **~~���·� �ֱ� ����
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
		
		//��й�ȣ ã��  
		@RequestMapping("/findpw")
		public ModelAndView findpw(@RequestParam String user_email,@RequestParam String user_id ){
			try{			
			User user = new User();
			user = userService.findByID(user_id);
			
			//�Է��� ���̵� ��ϵ� �̸��ϰ�  �Է��� �̸����� ���� ������ ����!
			String useremail = userService.finduseremail(user_id); 
			if(!useremail.equals(user_email)){
				ModelAndView mav = new ModelAndView();
				mav.setViewName("users/findemailerror");
				return mav;
			}
			
			Random rd = new Random();
	 		int num = rd.nextInt(10000)+1000; //���� ���� ���� ���ϱ� (10)�� 0~9�ϱ�... 10000�̸� 0~9999 ���⿡ õ�������ָ� 1000~10999(
			String pw = "cot"+Integer.toString(num) +"cot"; //�ʱ�ȭ�� ��й�ȣ ���� cot ���� cot 
			
			String password = pw; 
			
			//��� ��ȣ �ʱ�ȭ 
			user.setPassword(password);
			userService.update(user);
			
			//�̸��� ������ 
			String setfrom = "Cot.com";         //������ �̸��� �̸�?
			String tomail  = user_email;     // �޴� ��� �̸���
			String title   = "Cot ��й�ȣ ã��";      // ����
			String content = "��й�ȣ�� ������ ���� �ʱ�ȭ�˴ϴ�.\n"+"��й�ȣ��"+password+"�Դϴ�.";
			
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper  = new MimeMessageHelper(message, true, "UTF-8");
			 
			messageHelper.setFrom(setfrom);  // �����»�� �����ϰų� �ϸ� �����۵��� ����
			messageHelper.setTo(tomail);     // �޴»�� �̸���
			messageHelper.setSubject(title); // ���������� ������ �����ϴ�
			messageHelper.setText(content);  // ���� ����
			     
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
		
		//�޽��� �� ����
		@RequestMapping(value = "/formmessage", method = RequestMethod.GET)
		public ModelAndView formmessage(@RequestParam String writer,  HttpSession session) throws UnsupportedEncodingException {
			//������ ���
			String senduser = (String)session.getAttribute("userId");
			//�޴� ��� 
			writer  = new String(writer.getBytes("8859_1"),"UTF-8"); //get��Ŀ��� �ѱ��� ������ �����Ƿ�  �ٲ��ֱ�!
			String receiver = writer;
				
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("senduser", senduser);
			mav.addObject("receiver", receiver);
			
			
			mav.setViewName("/users/messageform");
			return mav;
		}
	
		//�޼��� ������ 	
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
		
		//�޼������� 
		@RequestMapping(value = "/message", method = RequestMethod.GET)
		public ModelAndView viewmessage(HttpSession session) {
			
			String userid = (String)session.getAttribute("userId");
			List<Message> message = userService.viewmessage(userid);
					
			ModelAndView mav = new ModelAndView();
			mav.addObject("message", message);
			mav.setViewName("/users/message");
			return mav;
		}
		
		//�����޼������� 
		@RequestMapping(value = "/viewsendmessage", method = RequestMethod.GET)
		public ModelAndView viewsendmessage(HttpSession session) {
			
			String userid = (String)session.getAttribute("userId");
			List<Message> message = userService.viewsendmessage(userid);
					
			ModelAndView mav = new ModelAndView();
			mav.addObject("message", message);
			mav.setViewName("/users/viewsendmessage");
			return mav;
		}

		
		
		//Ż��â �̵�
		@RequestMapping(value = "/unregisterform")
		public ModelAndView unregisterform(HttpSession session) {
					
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/users/unregisterform");
			return mav;
		}
		
		
		//ȸ�� Ż��
		@RequestMapping(value = "/unregister", method = RequestMethod.POST)
		public ModelAndView userunregister(HttpSession session, @RequestParam String user_password) {
			String userid = (String)session.getAttribute("userId");
			User user = new User();
			
			user = userService.findByID(userid);
			//��й�ȣ �� �� Ż��
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


