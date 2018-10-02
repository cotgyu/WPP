package com.wpp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.wpp.domain.WebBoard;
import com.wpp.service.UserService;
import com.wpp.service.WebBoardService;


@Controller
@RequestMapping("/webboard")
public class WebBoardController {
	
	@Autowired
	WebBoardService webboardService;
	@Autowired
	UserService userService;
	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);
	
	//게시판 리스트
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(defaultValue="title") String searchOption, @RequestParam(defaultValue="") String keyword,
			@RequestParam(defaultValue="1") int curPage) throws Exception{

		LOG.debug("-------------------WebBoardList_Start--------------------");		
		
		int count = webboardService.countboard(searchOption, keyword);
	
		BoardPage boardPage = new BoardPage(count, curPage);
		int start = boardPage.getPageBegin();
		int end = boardPage.getPageEnd();
		
		List<WebBoard> list = webboardService.Viewlist(start, end, searchOption, keyword);
		 
		List<WebBoard> poplist = webboardService.popboard();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list); 
		map.put("count", count); 
		map.put("searchOption", searchOption); 
		map.put("keyword", keyword); 
		map.put("boardPage", boardPage); 
		map.put("poplist", poplist);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map); 
        mav.setViewName("webboard/webboard"); 
        LOG.debug("-------------------WebBoardList_End--------------------");		
        return mav; 
	}
	
	
	// 게시물 작성화면 이동
	@RequestMapping(value="write", method=RequestMethod.GET)
    public String write(){
        return "webboard/write"; 
    }
	
	// 게시물 작성
	@RequestMapping(value="insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute WebBoard vo, HttpSession session) throws Exception{
	
	    String writer = (String) session.getAttribute("userId");
	   
	    vo.setWriter(writer);
	    webboardService.create(vo);
	    return "redirect:list";
	}

	// 게시물 보기
	@RequestMapping(value="view", method=RequestMethod.GET)
    public ModelAndView view(@RequestParam int bnum, HttpSession session) throws Exception{
       
		webboardService.uphit(bnum);
		
		String userId = webboardService.findByWriter(bnum);		
		String userimg = userService.findprofile(userId);	
       
        ModelAndView mav = new ModelAndView();
       
        mav.setViewName("webboard/view");
       
        mav.addObject("profileimg",userimg);
        mav.addObject("dto", webboardService.read(bnum));
        return mav;
    }
	
	//글 수정창으로 연결     
    @RequestMapping(value="/updatedetail/{bnum}", method=RequestMethod.GET)
    public ModelAndView boardDetail(@PathVariable("bnum") Integer bnum, ModelAndView mav) throws Exception{
        WebBoard vo = webboardService.detail(bnum);
      
        mav.setViewName("webboard/modify");
       
        mav.addObject("vo", vo);
       
        return mav;
    }
  
    // 게시글 수정
    @RequestMapping(value="update", method=RequestMethod.POST)
    public String update(@ModelAttribute WebBoard vo) throws Exception{
    	webboardService.update(vo);
        return "redirect:list";
    }
   
    // 게시글 삭제
    @RequestMapping("delete")
    public String delete(@RequestParam int bnum) throws Exception{
    	webboardService.delete(bnum);
        return "redirect:list";
    }
	
    //게시판 사진 업로드
    @RequestMapping(value="/photoUpload")
    public String photoUpload(HttpServletRequest request, PhotoVo vo){
        String callback = vo.getCallback();
        String callback_func = vo.getCallback_func();
        String file_result = "";
        try {
            if(vo.getFiledata() != null && vo.getFiledata().getOriginalFilename() != null && !vo.getFiledata().getOriginalFilename().equals("")){
                //파일이 존재하면
                String original_name = vo.getFiledata().getOriginalFilename();
                String ext = original_name.substring(original_name.lastIndexOf(".")+1);
                //파일 기본경로
                String defaultPath = request.getSession().getServletContext().getRealPath("/");
                //파일 기본경로 _ 상세경로
                String path = defaultPath + "resources" + File.separator + "Editors" + File.separator +"uploadimg" + File.separator;             
                File file = new File(path);
                
                //디렉토리 존재하지 않을경우 디렉토리 생성
                if(!file.exists()) {
                    file.mkdirs();
                }
                //업로드 할 파일명
                String realname = UUID.randomUUID().toString() + "." + ext;
            ///////////////// 서버에 파일쓰기 /////////////////
                vo.getFiledata().transferTo(new File(path+realname));
                file_result += "&bNewLine=true&sFileName="+original_name+"&sFileURL=/resources/Editors/uploadimg/"+realname;
            } else {
                file_result += "&errstr=error";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:" + callback + "?callback_func="+callback_func+file_result;
    }

   
}