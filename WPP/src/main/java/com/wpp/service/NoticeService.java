package com.wpp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.wpp.dao.NoticeDao;
import com.wpp.dao.UserDao;
import com.wpp.domain.Notice;


@Service
public class NoticeService {
	
	@Autowired
	NoticeDao noticeDao;
	@Autowired
	UserDao userDao;
	
	public int countboard(String searchOption, String keyword) throws Exception {
		return noticeDao.countboard(searchOption, keyword);
	}

	public List<Notice> Viewlist(int start, int end, String searchOption, String keyword) throws Exception {
		return noticeDao.Viewlist(start, end, searchOption, keyword);
	}

	public void create(Notice vo) throws Exception {
		noticeDao.create(vo);	
	}

	public void uphit(int bnum) throws Exception {
		noticeDao.uphit(bnum);
	}

	public String findByWriter(int bnum) {
		return noticeDao.findByWriter(bnum);
	}

	public Object read(int bnum) throws Exception {
		return noticeDao.read(bnum);
	}

	public Notice detail(Integer bnum) {		
		return noticeDao.detail(bnum);
	}

	public void update(Notice vo) throws Exception {
		noticeDao.update(vo);
	}

	public void delete(int bnum) throws Exception {
		noticeDao.delete(bnum);
	}

}
