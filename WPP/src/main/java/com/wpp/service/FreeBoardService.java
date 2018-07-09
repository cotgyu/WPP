package com.wpp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpp.dao.FreeBoardDao;
import com.wpp.dao.UserDao;
import com.wpp.domain.FreeBoard;

@Service
public class FreeBoardService {
	
	@Autowired
	FreeBoardDao freeboardDao;
	@Autowired
	UserDao userDao;
	
	public int countboard(String searchOption, String keyword) throws Exception {
		return freeboardDao.countboard(searchOption, keyword);
	}

	public List<FreeBoard> Viewlist(int start, int end, String searchOption, String keyword) throws Exception {
		return freeboardDao.Viewlist(start, end, searchOption, keyword);
	}

	public List<FreeBoard> popboard() throws Exception {
		return freeboardDao.popboard();
	}

	public void create(FreeBoard vo) throws Exception {
		freeboardDao.create(vo);	
	}

	public void uphit(int bnum) throws Exception {
		freeboardDao.uphit(bnum);
	}

	public String findByWriter(int bnum) {
		return freeboardDao.findByWriter(bnum);
	}

	public Object read(int bnum) throws Exception {
		return freeboardDao.read(bnum);
	}

	public FreeBoard detail(Integer bnum) {		
		return freeboardDao.detail(bnum);
	}

	public void update(FreeBoard vo) throws Exception {
		freeboardDao.update(vo);
	}

	public void delete(int bnum) throws Exception {
		freeboardDao.delete(bnum);
	}

}
