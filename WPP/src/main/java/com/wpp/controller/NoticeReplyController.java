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

import com.wpp.domain.NoticeReply;
import com.wpp.service.NoticeReplyService;



@RestController
@RequestMapping("/noticereply")
public class NoticeReplyController {
    
    @Autowired
    NoticeReplyService noticereplyService;
    
    // ��� �Է�
    @RequestMapping(value="insert.do", method=RequestMethod.POST)
    public ModelAndView insert(@ModelAttribute NoticeReply vo, HttpSession session,@RequestParam int bnum, @RequestParam String replytext){
    	
    	ModelAndView mav = new ModelAndView();
    	
        String userId = (String) session.getAttribute("userId");
    
        vo.setReplyer(userId);
        noticereplyService.create(vo);
        int rnum = vo.getRnum();  
        vo.setRegroup(rnum);
        noticereplyService.create_setgroup(vo);
        
        bnum = vo.getBnum();
        mav.addObject("bnum", bnum);
        mav.setViewName("redirect:/notice/view?bnum={bnum}");
        
        return mav;
  
    }
      
    // ��� ���
    @RequestMapping("list.do")
    public ModelAndView list(@RequestParam int bnum, ModelAndView mav){
        List<NoticeReply> list = noticereplyService.list(bnum);
    
        mav.setViewName("notice/replylist");	
     
        mav.addObject("bnum", bnum);
        mav.addObject("list", list);
      
        return mav;
    }
      
    //��� ����
    @RequestMapping("delete")
    public ModelAndView replydelete(@RequestParam int rnum, @RequestParam int bnum) throws Exception{
    	noticereplyService.delete(rnum);
    	
    	ModelAndView mav = new ModelAndView();
    	mav.addObject("bnum", bnum);
        mav.setViewName("redirect:/notice/view?bnum={bnum}");
    	
    	return mav;
    }
    
    //��� ����â���� ����     
    @RequestMapping(value="/detail/{rnum}", method=RequestMethod.GET)
    public ModelAndView replyDetail(@PathVariable("rnum") Integer rnum, ModelAndView mav){
        NoticeReply vo = noticereplyService.detail(rnum);
      
        mav.setViewName("notice/replymodify");
    
        mav.addObject("vo", vo);
     
        return mav;
    }
    
    //��� ����
    @RequestMapping(value="update", method=RequestMethod.POST)
    public ModelAndView replyupdate(@ModelAttribute NoticeReply vo,  @RequestParam int bnum, @RequestParam int rnum, @RequestParam String replytext) throws Exception{
    	ModelAndView mav = new ModelAndView();
    	
    	noticereplyService.update(vo);
    	
    	mav.addObject("bnum", bnum);
    	mav.setViewName("redirect:/notice/view?bnum={bnum}");
    		
    	return mav; 	
    }

  
    //�ڸ�Ʈ â �̵�
    @RequestMapping(value="/commentwrite/{rnum}", method=RequestMethod.GET)
    public ModelAndView commentwrite(@PathVariable("rnum") Integer rnum, ModelAndView mav){
        NoticeReply vo = noticereplyService.detail(rnum);
      
        mav.setViewName("notice/replycomment");
     
        mav.addObject("vo", vo);
      
        return mav;
    }    
    
    //�ڸ�Ʈ �ۼ�
    @RequestMapping(value="comment")
    public ModelAndView replycomment(@ModelAttribute NoticeReply vo, HttpSession session, @RequestParam int bnum, 
    		@RequestParam String replytext, @RequestParam int regroup, @RequestParam int restep, @RequestParam int reindent){
    	ModelAndView mav = new ModelAndView();
    	vo.setRegroup(regroup);
    	vo.setRestep(restep);
    	vo.setReindent(reindent);
    	noticereplyService.stepshape(vo); 
    	
    	String userId = (String)session.getAttribute("userId");
    	vo.setReplyer(userId);
    	
    	noticereplyService.createcomment(vo);
    	
    	bnum = vo.getBnum();        
        mav.addObject("bnum", bnum);
        mav.setViewName("redirect:/notice/view?bnum={bnum}");
        
    	return mav;
    }
}