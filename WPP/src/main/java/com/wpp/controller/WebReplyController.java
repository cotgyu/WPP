package com.wpp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wpp.domain.WebReply;
import com.wpp.service.WebReplyService;


@RestController
@RequestMapping("/webreply")
public class WebReplyController {
    
	@Autowired
	WebReplyService webReplyService;
    
    // ��� �Է�
    @RequestMapping(value="insert.do", method=RequestMethod.POST)
    public ModelAndView insert(@ModelAttribute WebReply vo, HttpSession session, @RequestParam int bnum, @RequestParam String replytext){
    	
    	ModelAndView mav = new ModelAndView();
    	
        String userId = (String) session.getAttribute("userId");
   
        
        vo.setReplyer(userId);
        webReplyService.create(vo);
        int rnum = vo.getRnum();
        vo.setRegroup(rnum);
        webReplyService.create_setgroup(vo);
        
        bnum = vo.getBnum();
        
        mav.addObject("bnum", bnum);
        mav.setViewName("redirect:/webboard/view?bnum={bnum}");
        
        return mav;
  
    }
      
    // ��� ���
    @RequestMapping("list.do")
    public ModelAndView list(@RequestParam int bnum, ModelAndView mav){
        List<WebReply> list = webReplyService.list(bnum);
        
        mav.setViewName("webboard/replylist");	
     
        mav.addObject("bnum", bnum);
        mav.addObject("list", list);
      
        return mav;
    }
      
    //��� ����
    @RequestMapping("delete")
    public ModelAndView replydelete(@RequestParam int rnum, @RequestParam int bnum) throws Exception{
    	webReplyService.delete(rnum);
    	
    	ModelAndView mav = new ModelAndView();
    	mav.addObject("bnum", bnum);
        mav.setViewName("redirect:/webboard/view?bnum={bnum}");
    	
    	return mav;
    }
    
  //��� ����â���� ����     
    @RequestMapping(value="/detail/{rnum}", method=RequestMethod.GET)
    public ModelAndView replyDetail(@PathVariable("rnum") Integer rnum, ModelAndView mav){
        WebReply vo = webReplyService.detail(rnum);
       
        mav.setViewName("webboard/replymodify");
       
        mav.addObject("vo", vo);
     
        return mav;
    }
    
    //��� ����
    @RequestMapping(value="update", method=RequestMethod.POST)
    public ModelAndView replyupdate(@ModelAttribute WebReply vo,  @RequestParam int bnum, @RequestParam int rnum, @RequestParam String replytext) throws Exception{
    	ModelAndView mav = new ModelAndView();
    	
    	webReplyService.update(vo);
    	
    	mav.addObject("bnum", bnum);
    	mav.setViewName("redirect:/webboard/view?bnum={bnum}");
    		
    	return mav; 	
    }

  
  //�ڸ�Ʈâ���� ����     
    @RequestMapping(value="/commentwrite/{rnum}", method=RequestMethod.GET)
    public ModelAndView commentwrite(@PathVariable("rnum") Integer rnum, ModelAndView mav){
        WebReply vo = webReplyService.detail(rnum);
      
        mav.setViewName("webboard/replycomment");
      
        mav.addObject("vo", vo);
      
        return mav;
    }    
    
    //�ڸ�Ʈ �ۼ�
    @RequestMapping(value="comment")
    public ModelAndView replycomment(@ModelAttribute WebReply vo, HttpSession session, @RequestParam int bnum, 
    		@RequestParam String replytext, @RequestParam int regroup, @RequestParam int restep, @RequestParam int reindent){
    	ModelAndView mav = new ModelAndView();
    	vo.setRegroup(regroup);
    	vo.setRestep(restep);
    	vo.setReindent(reindent);
    	webReplyService.stepshape(vo); 
    	
    	String userId = (String)session.getAttribute("userId");
    	vo.setReplyer(userId);
    	
    	webReplyService.createcomment(vo); 
    	
    	bnum = vo.getBnum();        
        mav.addObject("bnum", bnum);
        mav.setViewName("redirect:/webboard/view?bnum={bnum}");
        
    	return mav;
    }
}