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

import com.wpp.domain.FreeReply;
import com.wpp.service.FreeReplyService;


@RestController
@RequestMapping("/freereply")
public class FreeReplyController {
    
    @Autowired
    FreeReplyService freereplyService;
    
    // 댓글 입력
    @RequestMapping(value="insert.do", method=RequestMethod.POST)
    public ModelAndView insert(@ModelAttribute FreeReply vo, HttpSession session,@RequestParam int bnum, @RequestParam String replytext){
    	
    	//세션으로부터 userid 가져오기 
        String userId = (String) session.getAttribute("userId");
        //reply vo에 리플작성자 셋팅
        vo.setReplyer(userId);
        //댓글 생성
        freereplyService.create(vo);
        
        //댓글 순서관련 그룹 설정
        int rnum = vo.getRnum();  
        vo.setRegroup(rnum);
        
        //여기 2개는 하나의 서비스에 합쳐도 될듯 싶음
        freereplyService.create_setgroup(vo);
        freereplyService.stepshape(vo);
        //댓글 작성한 글로 가기위해 bnum 가져오기
        bnum = vo.getBnum();
        
        ModelAndView mav = new ModelAndView();
        mav.addObject("bnum", bnum);
        mav.setViewName("redirect:/freeboard/view?bnum={bnum}");
        
        return mav;
  
    }
      
    // 댓글 목록
    @RequestMapping("list.do")
    public ModelAndView list(@RequestParam int bnum, ModelAndView mav){
    	//bnum에 있는 댓글 리스트 가져오기 
        List<FreeReply> list = freereplyService.list(bnum);
        // 뷰이름 지정
        mav.setViewName("freeboard/replylist");	
        
        // mav에 데이터 추가 
        mav.addObject("bnum", bnum);
        mav.addObject("list", list);
        
        return mav;
    }

    //댓글 삭제
    @RequestMapping("delete")
    public ModelAndView replydelete(@RequestParam int rnum, @RequestParam int bnum) throws Exception{
    	//댓글번호(rnum)에 맞는 댓글 삭제 
    	freereplyService.delete(rnum);
    	
    	ModelAndView mav = new ModelAndView();
    	//게시물로 돌아가기위한 bnum 
    	mav.addObject("bnum", bnum);
        mav.setViewName("redirect:/freeboard/view?bnum={bnum}");
    	
    	return mav;
    }
    
    //댓글 수정창으로 연결     
    @RequestMapping(value="/detail/{rnum}", method=RequestMethod.GET)
    public ModelAndView replyDetail(@PathVariable("rnum") Integer rnum, ModelAndView mav){
    	
    	//댓글 번호(rnum)에 맞는 reply 불러오기 
        FreeReply vo = freereplyService.detail(rnum);
        // 뷰이름 지정
        mav.setViewName("freeboard/replymodify");
        // 뷰에 전달할 데이터 지정
        mav.addObject("vo", vo);
        // replyDetail.jsp로 포워딩
        return mav;
    }
    
    //댓글 수정
    @RequestMapping(value="update", method=RequestMethod.POST)
    public ModelAndView replyupdate(@ModelAttribute FreeReply vo,  @RequestParam int bnum) throws Exception{
    	ModelAndView mav = new ModelAndView();
    	//받아온 정보 vo로 수정
    	freereplyService.update(vo);
    	
    	mav.addObject("bnum", bnum);
    	mav.setViewName("redirect:/freeboard/view?bnum={bnum}");
    		
    	return mav; 	
    }
    
 
  
    //코맨트 작성 창 생성
    @RequestMapping(value="/commentwrite/{rnum}", method=RequestMethod.GET)
    public ModelAndView commentwrite(@PathVariable("rnum") Integer rnum, ModelAndView mav){
    	//코멘트달려는 댓글 정보 가져오기
        FreeReply vo = freereplyService.detail(rnum);
        // 뷰이름 지정
        mav.setViewName("freeboard/replycomment");
        // 뷰에 전달할 데이터 지정
        mav.addObject("vo", vo);
        return mav;
    }    
    
    //코멘트 작성
    @RequestMapping(value="comment")
    public ModelAndView replycomment(@ModelAttribute FreeReply vo, HttpSession session, @RequestParam int bnum, 
    		@RequestParam String replytext, @RequestParam int regroup, @RequestParam int restep, @RequestParam int reindent){
    	ModelAndView mav = new ModelAndView();
    	//댓글 순서 위한 셋팅
    	vo.setRegroup(regroup);
    	vo.setRestep(restep);
    	vo.setReindent(reindent);
    	
    	freereplyService.stepshape(vo); 
    	
    	String userId = (String)session.getAttribute("userId");
    	vo.setReplyer(userId);
    	
    	freereplyService.createcomment(vo); //코멘트생성
    
    	bnum = vo.getBnum();        
        mav.addObject("bnum", bnum);
        mav.setViewName("redirect:/freeboard/view?bnum={bnum}");
        
    	return mav;
    }
}