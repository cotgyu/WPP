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

import com.wpp.domain.QnAReply;
import com.wpp.service.QnAReplyService;





@RestController
@RequestMapping("/qnareply")
public class QnAReplyController {
    
    @Autowired
    QnAReplyService qnareplyService;
    
    // 댓글 입력
    @RequestMapping(value="insert.do", method=RequestMethod.POST)
    public ModelAndView insert(@ModelAttribute QnAReply vo, HttpSession session,@RequestParam int bnum, @RequestParam String replytext){
    	
    	ModelAndView mav = new ModelAndView();
    	
        String userId = (String) session.getAttribute("userId");
    
        vo.setReplyer(userId);
        
        qnareplyService.create(vo);
        int rnum = vo.getRnum();  
        vo.setRegroup(rnum);
        qnareplyService.create_setgroup(vo);
        
        bnum = vo.getBnum();
        mav.addObject("bnum", bnum);
        mav.setViewName("redirect:/qna/view?bnum={bnum}");
        
        return mav;
  
    }
      
    // 댓글 목록
    @RequestMapping("list.do")
    public ModelAndView list(@RequestParam int bnum, ModelAndView mav){
        List<QnAReply> list = qnareplyService.list(bnum);
      
        mav.setViewName("qna/replylist");	
      
        mav.addObject("bnum", bnum);
        mav.addObject("list", list);
    
        return mav;
    }
      
    //댓글 삭제
    @RequestMapping("delete")
    public ModelAndView replydelete(@RequestParam int rnum, @RequestParam int bnum) throws Exception{
    	qnareplyService.delete(rnum);
    	
    	ModelAndView mav = new ModelAndView();
    	mav.addObject("bnum", bnum);
        mav.setViewName("redirect:/qna/view?bnum={bnum}");
    	
    	return mav;
    }
    
    //댓글 수정창으로 연결     
    @RequestMapping(value="/detail/{rnum}", method=RequestMethod.GET)
    public ModelAndView replyDetail(@PathVariable("rnum") Integer rnum, ModelAndView mav){
        QnAReply vo = qnareplyService.detail(rnum);
     
        mav.setViewName("qna/replymodify");
  
        mav.addObject("vo", vo);

        return mav;
    }
    
    //댓글 수정
    @RequestMapping(value="update", method=RequestMethod.POST)
    public ModelAndView replyupdate(@ModelAttribute QnAReply vo,  @RequestParam int bnum, @RequestParam int rnum, @RequestParam String replytext) throws Exception{
    	ModelAndView mav = new ModelAndView();
    	
    	qnareplyService.update(vo);
    	
    	mav.addObject("bnum", bnum);
    	mav.setViewName("redirect:/qna/view?bnum={bnum}");
    		
    	return mav; 	
    }

    //코멘트 작성창으로 이동     
    @RequestMapping(value="/commentwrite/{rnum}", method=RequestMethod.GET)
    public ModelAndView commentwrite(@PathVariable("rnum") Integer rnum, ModelAndView mav){
        QnAReply vo = qnareplyService.detail(rnum);
     
        mav.setViewName("qna/replycomment");
      
        mav.addObject("vo", vo);
       
        return mav;
    }    
    
    //코멘트 작성
    @RequestMapping(value="comment")
    public ModelAndView replycomment(@ModelAttribute QnAReply vo, HttpSession session, @RequestParam int bnum, 
    		@RequestParam String replytext, @RequestParam int regroup, @RequestParam int restep, @RequestParam int reindent){
    	ModelAndView mav = new ModelAndView();
    	vo.setRegroup(regroup);
    	vo.setRestep(restep);
    	vo.setReindent(reindent);
    	qnareplyService.stepshape(vo); 
    	
    	String userId = (String)session.getAttribute("userId");
    	vo.setReplyer(userId);
    	
    	qnareplyService.createcomment(vo); 
    
    	bnum = vo.getBnum();        
        mav.addObject("bnum", bnum);
        mav.setViewName("redirect:/qna/view?bnum={bnum}");
        
    	return mav;
    }
}