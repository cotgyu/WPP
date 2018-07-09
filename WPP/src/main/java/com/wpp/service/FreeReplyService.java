package com.wpp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpp.dao.FreeReplyDao;
import com.wpp.domain.FreeReply;

@Service
public class FreeReplyService {
	
	@Autowired
	FreeReplyDao freereplyDao;
	public void create(FreeReply vo) {
		freereplyDao.create(vo);
	}

	public List<FreeReply> list(int bnum) {
		return freereplyDao.list(bnum);
	}

	public void delete(int rnum) {
		freereplyDao.delete(rnum);
	}

	public void update(FreeReply vo) throws Exception {
		freereplyDao.update(vo);
	}

	public FreeReply detail(Integer rnum) {
		
		return freereplyDao.detail(rnum);
	}

	public void stepshape(FreeReply vo) {
		freereplyDao.stepshape(vo);
	}

	public void createcomment(FreeReply vo) {
		freereplyDao.createcomment(vo);
	}

	public void create_setgroup(FreeReply vo) {
		freereplyDao.create_setgroup(vo);
	}

}
