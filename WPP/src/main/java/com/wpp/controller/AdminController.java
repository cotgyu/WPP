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
	
	//������ ��й�ȣ Ȯ�� â���� �̵�
	@RequestMapping(value="adminform")
    public ModelAndView form() throws Exception{
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/admin/adminlogin");
        return mav;
    }
	
	//��й�ȣ �Է� �� ������ Ȯ�� 
	@RequestMapping(value="login", method=RequestMethod.POST)
    public ModelAndView login(@RequestParam String password) throws Exception{
		//������ ���� ã�� 
		User user = userService.findByID("관리자");
		
		//�Է��� ��й�ȣ�� ������ ���� ��й�ȣ �� 
		if(passwordEncoder.matches(password ,user.getPassword())){
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/admin/list");
			return mav;
		}
		//��й�ȣ ������ â �̵� 
		else{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/loginfail");
		return mav;
		}
    }
	
	
	//�Խù� ��� �����ֱ�
	@RequestMapping(value="list")
    public ModelAndView list(@RequestParam(defaultValue="title") String searchOption, 
			@RequestParam(defaultValue="") String keyword,
			@RequestParam(defaultValue="1") int curPage) throws Exception{
		
		
		//���ڵ��� ����
		int count = adminService.countboard(searchOption, keyword);
		//������
		BoardPage boardPage = new BoardPage(count, curPage);
		int start = boardPage.getPageBegin();
		int end = boardPage.getPageEnd();
		List<WebBoard> list = adminService.Viewlist(start, end, searchOption, keyword);
		
		
		//�����͸� �ʿ� ����
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list); //list
		map.put("count", count); //���ڵ� ����
		map.put("searchOption", searchOption); //�˻� �ɼ�
		map.put("keyword", keyword); //�˻� Ű����
		map.put("boardPage", boardPage); 
		
		//�𵨰� ��
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map); // �ʿ� ����� �����͸� mav(�� �� ��)�� ����
    			
		mav.setViewName("/admin/adminmode");
		return mav;
	
    }
	
	//��� ��� �����ֱ�
		@RequestMapping(value="replylist")
	    public ModelAndView replylist(@RequestParam(defaultValue="replytext") String searchOption, 
				@RequestParam(defaultValue="") String keyword,
				@RequestParam(defaultValue="1") int curPage) throws Exception{
			
			
			//���ڵ��� ����
			int countreply = adminService.countreply(searchOption, keyword);
			//������
			BoardPage boardPage = new BoardPage(countreply, curPage);
			int start = boardPage.getPageBegin();
			int end = boardPage.getPageEnd();
			List<WebReply> list = adminService.Viewreplylist(start, end, searchOption, keyword);
			
			//�����͸� �ʿ� ����
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", list); //list
			map.put("countreply", countreply); //���ڵ� ����
			map.put("searchOption", searchOption); //�˻� �ɼ�
			map.put("keyword", keyword); //�˻� Ű����
			map.put("boardPage", boardPage); 
			
			//�𵨰� ��
			ModelAndView mav = new ModelAndView();
			mav.addObject("map", map); // �ʿ� ����� �����͸� mav�� ����
	    			
			mav.setViewName("/admin/adminmode_reply");
			return mav;
		
	    }
	
		//���� ����Ʈ 
		@RequestMapping(value="userlist")
	    public ModelAndView userlist(@RequestParam(defaultValue="name") String searchOption, 
				@RequestParam(defaultValue="") String keyword,
				@RequestParam(defaultValue="1") int curPage) throws Exception{
			
			
			//���ڵ��� ����
			int countuser = adminService.countuser(searchOption, keyword);
			//������
			BoardPage boardPage = new BoardPage(countuser, curPage);
			int start = boardPage.getPageBegin();
			int end = boardPage.getPageEnd();
			List<User> list = adminService.Viewuserlist(start, end, searchOption, keyword);
			
			//�����͸� �ʿ� ����
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", list); //list
			map.put("countuser", countuser); //���ڵ� ����
			map.put("searchOption", searchOption); //�˻� �ɼ�
			map.put("keyword", keyword); //�˻� Ű����
			map.put("boardPage", boardPage); 
			
			//�𵨰� ��
			ModelAndView mav = new ModelAndView();
			mav.addObject("map", map); // �ʿ� ����� �����͸� mav�� ����
	    			
			mav.setViewName("/admin/adminmode_users");
			return mav;
		
	    }
		
		//������ ȸ��Ż��
		@RequestMapping("/userunregister/{username}")
		public ModelAndView userUnregi(@PathVariable("username") String username){
			
			userService.unregister(username);
			
			
			ModelAndView mav = new ModelAndView();
	    			
			mav.setViewName("redirect:/admin/userlist");
			return mav;
		}
	
}