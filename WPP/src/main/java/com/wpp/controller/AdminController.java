package com.wpp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.wpp.common.BoardPage;
import com.wpp.domain.QnA;
import com.wpp.domain.User;
import com.wpp.domain.WebBoard;
import com.wpp.domain.WebReply;
import com.wpp.service.AdminService;
import com.wpp.service.FreeBoardService;
import com.wpp.service.NoticeService;
import com.wpp.service.QnAService;
import com.wpp.service.UserService;
import com.wpp.service.WebBoardService;



@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private AdminService adminService;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	//관리자 비밀번호 확인창으로 이동
	@RequestMapping(value="adminform")
    public ModelAndView form() throws Exception{
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/admin/adminlogin");
        return mav;
    }
	
	//비밀번호 입력 후 관리자 확인
	@RequestMapping(value="login", method=RequestMethod.POST)
    public ModelAndView login(@RequestParam String password) throws Exception{
		//관리자 유저 찾기
		User user = userService.findByID("관리자");
		
		//입력한 비밀번호화 관리자 비밀번호 비교 
		if(passwordEncoder.matches(password ,user.getPassword())){
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/admin/list");
			return mav;
		}
		//비밀번호 오류시 실패창 이동
		else{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/loginfail");
		return mav;
		}
    }
	
	
	//모든 게시물 모여주기 
	@RequestMapping(value="list")
    public ModelAndView list(@RequestParam(defaultValue="title") String searchOption, 
			@RequestParam(defaultValue="") String keyword,
			@RequestParam(defaultValue="1") int curPage) throws Exception{
		
		
		//글 카운트 
		int count = adminService.countboard(searchOption, keyword);
		//페이지 설정 관련
		BoardPage boardPage = new BoardPage(count, curPage);
		int start = boardPage.getPageBegin();
		int end = boardPage.getPageEnd();
		List<WebBoard> list = adminService.Viewlist(start, end, searchOption, keyword);
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list); //list
		map.put("count", count); //글 개수 
		map.put("searchOption", searchOption); //검색 옵션
		map.put("keyword", keyword); //검색 키워드 
		map.put("boardPage", boardPage); 
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map); //맵에 저장된 데이터를 Model And View 에 저장
    			
		mav.setViewName("/admin/adminmode");
		return mav;
	
    }
	
	//모든 댓글 보기 
		@RequestMapping(value="replylist")
	    public ModelAndView replylist(@RequestParam(defaultValue="replytext") String searchOption, 
				@RequestParam(defaultValue="") String keyword,
				@RequestParam(defaultValue="1") int curPage) throws Exception{
			
			
			//댓글 카운트
			int countreply = adminService.countreply(searchOption, keyword);
			//페이지 설정 
			BoardPage boardPage = new BoardPage(countreply, curPage);
			int start = boardPage.getPageBegin();
			int end = boardPage.getPageEnd();
			List<WebReply> list = adminService.Viewreplylist(start, end, searchOption, keyword);
			

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", list); 
			map.put("countreply", countreply); 
			map.put("searchOption", searchOption); 
			map.put("keyword", keyword); 
			map.put("boardPage", boardPage); 
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("map", map); 
	    			
			mav.setViewName("/admin/adminmode_reply");
			return mav;
		
	    }
	
		//모든 유저 보기 
		@RequestMapping(value="userlist")
	    public ModelAndView userlist(@RequestParam(defaultValue="name") String searchOption, 
				@RequestParam(defaultValue="") String keyword,
				@RequestParam(defaultValue="1") int curPage) throws Exception{
			
			
			
			int countuser = adminService.countuser(searchOption, keyword);
		
			BoardPage boardPage = new BoardPage(countuser, curPage);
			int start = boardPage.getPageBegin();
			int end = boardPage.getPageEnd();
			List<User> list = adminService.Viewuserlist(start, end, searchOption, keyword);
			
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", list);
			map.put("countuser", countuser); 
			map.put("searchOption", searchOption); 
			map.put("keyword", keyword); 
			map.put("boardPage", boardPage); 
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("map", map);
	    			
			mav.setViewName("/admin/adminmode_users");
			return mav;
		
	    }
		
		//유저 탈퇴 시키기 
		@RequestMapping("/userunregister/{username}")
		public ModelAndView userUnregi(@PathVariable("username") String username){
			
			userService.unregister(username);
			
			
			ModelAndView mav = new ModelAndView();
	    			
			mav.setViewName("redirect:/admin/userlist");
			return mav;
		}
	
}