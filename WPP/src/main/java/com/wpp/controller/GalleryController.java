package com.wpp.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.wpp.common.BoardPage;
import com.wpp.domain.Gallery;
import com.wpp.service.GalleryService;


@Controller
@RequestMapping("/gallery")
public class GalleryController {

	@Autowired
	GalleryService galleryService;

	
	@Resource(name="uploadPath")
	String uploadPath;
	
	//갤러리 리스트
    @RequestMapping("list")
	public ModelAndView list(@RequestParam(defaultValue="imgname") String searchOption, 
			@RequestParam(defaultValue="") String keyword,
			@RequestParam(defaultValue="1") int curPage) throws Exception{
		
		//레코드의 개수
		int count = galleryService.countboard(searchOption, keyword);
		
		//페이지
		BoardPage boardPage = new BoardPage(count, curPage);
		int start = boardPage.getPageBegin();
		int end = boardPage.getPageEnd();
		
		List<Gallery> ilist = galleryService.viewimglist(start, end, searchOption, keyword);
		
		
		//데이터를 맵에 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ilist", ilist); //list
		map.put("count", count); //레코드 개수
		map.put("searchOption", searchOption); //검색 옵션
		map.put("keyword", keyword); //검색 키워드
		map.put("boardPage", boardPage); 
		
		//모델과 뷰
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map); // 맵에 저장된 데이터를 mav에 저장
        mav.setViewName("gallery/imagegallery"); // 뷰를 list.jsp로 설정
       
        return mav; // list.jsp로 List가 전달된다.
    }
    
	// 이미지 작성화면 이동
	@RequestMapping(value="write", method=RequestMethod.GET)
    public String write(){
        return "gallery/imagewrite"; // write.jsp로 이동
    }
	

    //이미지 작성 
	@RequestMapping(value="insert", method=RequestMethod.POST)
	public ModelAndView insert(@ModelAttribute Gallery vo, HttpSession session, MultipartFile file, ModelAndView mav, @RequestParam String imgname) throws Exception{
	    // session에 저장된 userId를 writer에 저장
	    String imgwriter = (String) session.getAttribute("userId");
	    // vo에 writer를 세팅
	    vo.setImgwriter(imgwriter);
	    
	    //파일이름 중복을 막기위한 랜덤이름 붙이기
	    UUID uuid = UUID.randomUUID();	    
        String savedName = uuid.toString()+"_"+file.getOriginalFilename();
        File target = new File(uploadPath, savedName);
        
        // 임시디렉토리에 저장된 업로드된 파일을 지정된 디렉토리로 복사
        // FileCopyUtils.copy(바이트배열, 파일객체)
        FileCopyUtils.copy(file.getBytes(), target);

  
        mav.addObject("savedName", savedName);
        //vo에 데이터 넣기
        vo.setImgfile(savedName);
        vo.setImgname(imgname);
        
        galleryService.imageinsert(vo);
        
        mav.setViewName("redirect:/gallery/list");
        return mav;
	}

	//이미지 추천 
    @RequestMapping(value="/up/{imgid}",method=RequestMethod.GET)
    public String up(@PathVariable("imgid") Integer imgid){
    	//이미지 번호 받아와서 추천   	
    	galleryService.imageup(imgid);
    	return "redirect:/gallery/list";
    }
    
    //추천 순  정렬
    @RequestMapping(value="/orderbyup",method=RequestMethod.GET)
    public ModelAndView orderbyup(@RequestParam(defaultValue="imgname") String searchOption, 
							@RequestParam(defaultValue="") String keyword,
							@RequestParam(defaultValue="1") int curPage){
    	
    	//레코드의 개수
    	int count = galleryService.countboard(searchOption, keyword);
    			
    	//페이지
    	BoardPage boardPage = new BoardPage(count, curPage);
    	int start = boardPage.getPageBegin();
    	int end = boardPage.getPageEnd();
    			
    	List<Gallery> orderbyup = galleryService.uplist(start, end, searchOption, keyword);
    			
    			
    	//데이터를 맵에 저장
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("ilist", orderbyup); //list
    	map.put("count", count); //레코드 개수
    	map.put("searchOption", searchOption); //검색 옵션
    	map.put("keyword", keyword); //검색 키워드
    	map.put("boardPage", boardPage); 
    			
    	
    	ModelAndView mav = new ModelAndView();
    	mav.addObject("map", map); 
    	mav.setViewName("gallery/imagegallery"); 
    	       
    	return mav; 
    	
    }
    
    //이미지 삭제
    @RequestMapping(value="/delete/{imgid}",method=RequestMethod.GET)
    public String delete(@PathVariable("imgid") Integer imgid){
    	//이미지 번호 받아와서 이미지 삭제
    	galleryService.imagedelete(imgid);
    	return "redirect:/gallery/list";
    }
}