package com.wpp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpp.dao.QnAReplyDao;
import com.wpp.domain.QnAReply;




@Service
public class QnAReplyService {
	
	@Autowired
	QnAReplyDao qnareplyDao;
	public void create(QnAReply vo) {
		qnareplyDao.create(vo);
	}

	public List<QnAReply> list(int bnum) {
		return qnareplyDao.list(bnum);
	}

	public void delete(int rnum) {
		qnareplyDao.delete(rnum);
	}

	public void update(QnAReply vo) throws Exception {
		qnareplyDao.update(vo);
	}

	public QnAReply detail(Integer rnum) {
		
		return qnareplyDao.detail(rnum);
	}

	public void stepshape(QnAReply vo) {
		qnareplyDao.stepshape(vo);
	}

	public void createcomment(QnAReply vo) {
		qnareplyDao.createcomment(vo);
	}

	public void create_setgroup(QnAReply vo) {
		qnareplyDao.create_setgroup(vo);
	}

}
