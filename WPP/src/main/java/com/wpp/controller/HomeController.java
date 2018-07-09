package com.wpp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wpp.dao.FreeBoardDao;
import com.wpp.dao.GalleryDao;
import com.wpp.dao.QnADao;
import com.wpp.dao.UserDao;
import com.wpp.dao.WebBoardDao;
import com.wpp.domain.FreeBoard;
import com.wpp.domain.Gallery;
import com.wpp.domain.QnA;

import com.wpp.domain.WebBoard;



@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired
	private WebBoardDao webboardDao;
	@Autowired
	private FreeBoardDao freeboardDao;
	@Autowired
	private GalleryDao galleryDao;
	@Autowired
	private QnADao qnaDao;
	@Autowired
	private UserDao userDao;

	//����ȭ��
	@RequestMapping("/")
	public ModelAndView Home(HttpSession session) throws Exception{
		//�α�Խ��� ��������
		List<WebBoard> poplist = webboardDao.popboard();
		List<FreeBoard> popFlist = freeboardDao.popboard();
		List<Gallery> popImglist = galleryDao.poplist();
		List<QnA> popQnalist = qnaDao.popboard();
		//�ֽ� �� ��������
		List<WebBoard> recentlist = webboardDao.recentboard(); 
	
		//�����͸� �ʿ� ����
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("poplist", poplist);
		map.put("popFlist", popFlist);
		map.put("popImglist",popImglist);
		map.put("popQnalist",popQnalist);
		map.put("recentlist", recentlist);
	
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map); 
        mav.setViewName("home"); 
       
        return mav; 
	}
	
	

}