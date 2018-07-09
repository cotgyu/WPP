package com.wpp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpp.dao.QnADao;
import com.wpp.dao.UserDao;
import com.wpp.domain.FreeBoard;
import com.wpp.domain.QnA;



@Service
public class QnAService {
	
	@Autowired
	QnADao qnaDao;
	@Autowired
	UserDao userDao;
	
	public int countboard(String searchOption, String keyword) throws Exception {
		return qnaDao.countboard(searchOption, keyword);
	}

	public List<QnA> Viewlist(int start, int end, String searchOption, String keyword) throws Exception {
		return qnaDao.Viewlist(start, end, searchOption, keyword);
	}

	public void create(QnA vo) throws Exception {
		qnaDao.create(vo);
	}

	public void uphit(int bnum) throws Exception {
		qnaDao.uphit(bnum);
	}

	public String findByWriter(int bnum) {
		return qnaDao.findByWriter(bnum);
	}

	public Object read(int bnum) throws Exception {
		return qnaDao.read(bnum);
	}

	public QnA detail(Integer bnum) {		
		return qnaDao.detail(bnum);
	}

	public void update(QnA vo) throws Exception {
		qnaDao.update(vo);
	}

	public void delete(int bnum) throws Exception {
		qnaDao.delete(bnum);
	}

	public List<QnA> popboard() throws Exception{
		return qnaDao.popboard();
	}

	public void setgroup(QnA vo) {
		qnaDao.setgroup(vo);
	}

}
