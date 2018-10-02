package com.wpp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);
	//메인화면
	@RequestMapping("/")
	public ModelAndView Home(HttpSession session) throws Exception{
		LOG.debug("-------------------Home_Start--------------------");
		
		//인기게시판 가져오기
		List<WebBoard> poplist = webboardDao.popboard();
		List<FreeBoard> popFlist = freeboardDao.popboard();
		List<Gallery> popImglist = galleryDao.poplist();
		List<QnA> popQnalist = qnaDao.popboard();
		//최신 글 가져오기
		List<WebBoard> recentlist = webboardDao.recentboard(); 
	
		//데이터를 맵에 저장
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("poplist", poplist);
		map.put("popFlist", popFlist);
		map.put("popImglist",popImglist);
		map.put("popQnalist",popQnalist);
		map.put("recentlist", recentlist);
	
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map); 
        mav.setViewName("home"); 
        LOG.debug("-------------------Home_End--------------------");
        return mav; 
	}
	
	

}