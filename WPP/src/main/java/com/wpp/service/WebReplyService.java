package com.wpp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpp.dao.WebReplyDao;
import com.wpp.domain.WebReply;
@Service
public class WebReplyService {

	@Autowired
	WebReplyDao webreplyDao;
	
	public void create(WebReply vo) {
		webreplyDao.create(vo);
		
	}

	public List<WebReply> list(int bnum) {
		return webreplyDao.list(bnum);
	}

	public void delete(int rnum) {
		webreplyDao.delete(rnum);
		
	}

	public WebReply detail(Integer rnum) {
		return webreplyDao.detail(rnum);
	}

	public void stepshape(WebReply vo) {
		webreplyDao.stepshape(vo);
		
	}

	public void createcomment(WebReply vo) {
		webreplyDao.createcomment(vo);
	}

	public void update(WebReply vo) throws Exception {
		webreplyDao.update(vo);
	}

	public void create_setgroup(WebReply vo) {
		webreplyDao.create_setgroup(vo);
		
	}

}
