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
	
	//������ ����Ʈ
    @RequestMapping("list")
	public ModelAndView list(@RequestParam(defaultValue="imgname") String searchOption, 
			@RequestParam(defaultValue="") String keyword,
			@RequestParam(defaultValue="1") int curPage) throws Exception{
		
		//���ڵ��� ����
		int count = galleryService.countboard(searchOption, keyword);
		
		//������
		BoardPage boardPage = new BoardPage(count, curPage);
		int start = boardPage.getPageBegin();
		int end = boardPage.getPageEnd();
		
		List<Gallery> ilist = galleryService.viewimglist(start, end, searchOption, keyword);
		
		
		//�����͸� �ʿ� ����
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ilist", ilist); //list
		map.put("count", count); //���ڵ� ����
		map.put("searchOption", searchOption); //�˻� �ɼ�
		map.put("keyword", keyword); //�˻� Ű����
		map.put("boardPage", boardPage); 
		
		//�𵨰� ��
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map); // �ʿ� ����� �����͸� mav�� ����
        mav.setViewName("gallery/imagegallery"); // �並 list.jsp�� ����
       
        return mav; // list.jsp�� List�� ���޵ȴ�.
    }
    
	// �̹��� �ۼ�ȭ�� �̵�
	@RequestMapping(value="write", method=RequestMethod.GET)
    public String write(){
        return "gallery/imagewrite"; // write.jsp�� �̵�
    }
	

    //�̹��� �ۼ� 
	@RequestMapping(value="insert", method=RequestMethod.POST)
	public ModelAndView insert(@ModelAttribute Gallery vo, HttpSession session, MultipartFile file, ModelAndView mav, @RequestParam String imgname) throws Exception{
	    // session�� ����� userId�� writer�� ����
	    String imgwriter = (String) session.getAttribute("userId");
	    // vo�� writer�� ����
	    vo.setImgwriter(imgwriter);
	    
	    //�����̸� �ߺ��� �������� �����̸� ���̱�
	    UUID uuid = UUID.randomUUID();	    
        String savedName = uuid.toString()+"_"+file.getOriginalFilename();
        File target = new File(uploadPath, savedName);
        
        // �ӽõ��丮�� ����� ���ε�� ������ ������ ���丮�� ����
        // FileCopyUtils.copy(����Ʈ�迭, ���ϰ�ü)
        FileCopyUtils.copy(file.getBytes(), target);

  
        mav.addObject("savedName", savedName);
        //vo�� ������ �ֱ�
        vo.setImgfile(savedName);
        vo.setImgname(imgname);
        
        galleryService.imageinsert(vo);
        
        mav.setViewName("redirect:/gallery/list");
        return mav;
	}

	//�̹��� ��õ 
    @RequestMapping(value="/up/{imgid}",method=RequestMethod.GET)
    public String up(@PathVariable("imgid") Integer imgid){
    	//�̹��� ��ȣ �޾ƿͼ� ��õ   	
    	galleryService.imageup(imgid);
    	return "redirect:/gallery/list";
    }
    
    //��õ ��  ����
    @RequestMapping(value="/orderbyup",method=RequestMethod.GET)
    public ModelAndView orderbyup(@RequestParam(defaultValue="imgname") String searchOption, 
							@RequestParam(defaultValue="") String keyword,
							@RequestParam(defaultValue="1") int curPage){
    	
    	//���ڵ��� ����
    	int count = galleryService.countboard(searchOption, keyword);
    			
    	//������
    	BoardPage boardPage = new BoardPage(count, curPage);
    	int start = boardPage.getPageBegin();
    	int end = boardPage.getPageEnd();
    			
    	List<Gallery> orderbyup = galleryService.uplist(start, end, searchOption, keyword);
    			
    			
    	//�����͸� �ʿ� ����
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("ilist", orderbyup); //list
    	map.put("count", count); //���ڵ� ����
    	map.put("searchOption", searchOption); //�˻� �ɼ�
    	map.put("keyword", keyword); //�˻� Ű����
    	map.put("boardPage", boardPage); 
    			
    	
    	ModelAndView mav = new ModelAndView();
    	mav.addObject("map", map); 
    	mav.setViewName("gallery/imagegallery"); 
    	       
    	return mav; 
    	
    }
    
    //�̹��� ����
    @RequestMapping(value="/delete/{imgid}",method=RequestMethod.GET)
    public String delete(@PathVariable("imgid") Integer imgid){
    	//�̹��� ��ȣ �޾ƿͼ� �̹��� ����
    	galleryService.imagedelete(imgid);
    	return "redirect:/gallery/list";
    }
}