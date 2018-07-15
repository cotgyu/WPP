package com.wpp.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.wpp.common.BoardPage;
import com.wpp.common.PhotoVo;
import com.wpp.domain.Notice;
import com.wpp.service.NoticeService;
import com.wpp.service.UserService;


@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private UserService userService;
	
	//공지사항 리스트
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(defaultValue="title") String searchOption, 
			@RequestParam(defaultValue="") String keyword,
			@RequestParam(defaultValue="1") int curPage) throws Exception{
		
		
		int count = noticeService.countboard(searchOption, keyword);
		
		
		BoardPage boardPage = new BoardPage(count, curPage);
		int start = boardPage.getPageBegin();
		int end = boardPage.getPageEnd();
		
		List<Notice> list = noticeService.Viewlist(start, end, searchOption, keyword);
		

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list); 
		map.put("count", count); 
		map.put("searchOption", searchOption); 
		map.put("keyword", keyword); 
		map.put("boardPage", boardPage); 
		
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map); 
        mav.setViewName("notice/notice"); 
       
        return mav; 
	}
	
	// 공지 작성화면 이동
	@RequestMapping(value="write", method=RequestMethod.GET)
    public String write(){
        return "notice/write"; 
    }
	
	// 공지 작성
	@RequestMapping(value="insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute Notice vo, HttpSession session) throws Exception{
	   
	    String writer = (String) session.getAttribute("userId");
	   
	    vo.setWriter(writer);
	    noticeService.create(vo);
	    return "redirect:list";
	}

	// 게시물 보기
	@RequestMapping(value="view", method=RequestMethod.GET)
    public ModelAndView view(@RequestParam int bnum, HttpSession session) throws Exception{
      
		noticeService.uphit(bnum);
	
		String userId = noticeService.findByWriter(bnum);
		String userimg = userService.findprofile(userId);	
      
        ModelAndView mav = new ModelAndView();
      
        mav.setViewName("notice/view");
    
        mav.addObject("dto", noticeService.read(bnum));
        mav.addObject("profileimg",userimg);
        return mav;
    }
  
	//글 수정창으로 연결     
    @RequestMapping(value="/updatedetail/{bnum}", method=RequestMethod.GET)
    public ModelAndView boardDetail(@PathVariable("bnum") Integer bnum, ModelAndView mav){
        Notice vo = noticeService.detail(bnum);
      
        mav.setViewName("notice/modify");
     
        mav.addObject("vo", vo);
     
        return mav;
    }
    // 게시글 수정
      @RequestMapping(value="update", method=RequestMethod.POST)
    public String update(@ModelAttribute Notice vo) throws Exception{
    	noticeService.update(vo);
        return "redirect:list";
    }
   
    // 게시글 삭제
    @RequestMapping("delete")
    public String delete(@RequestParam int bnum) throws Exception{
    	noticeService.delete(bnum);
        return "redirect:list";
    }
    


 
	
}