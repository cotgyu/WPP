package com.wpp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpp.dao.AdminDao;
import com.wpp.domain.User;
import com.wpp.domain.WebBoard;
import com.wpp.domain.WebReply;

@Service
public class AdminService {
	
	@Autowired
	AdminDao adminDao;

	public int countboard(String searchOption, String keyword) {
		return adminDao.countboard(searchOption, keyword);
	}

	public List<WebBoard> Viewlist(int start, int end, String searchOption, String keyword) {
		return adminDao.ViewList(start, end, searchOption, keyword);
	}
	
	public int countreply(String searchOption, String keyword) {
		return adminDao.countreply(searchOption, keyword);
	}

	public List<WebReply> Viewreplylist(int start, int end, String searchOption, String keyword) {
		return adminDao.Viewreplylist(start, end, searchOption, keyword);
	}

	public int countuser(String searchOption, String keyword) {
		return adminDao.countuser(searchOption, keyword);
	}

	public List<User> Viewuserlist(int start, int end, String searchOption, String keyword) {
		return  adminDao.Viewuserlist(start, end, searchOption, keyword);
	}

}
