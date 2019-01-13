package com.wpp.service;

import java.sql.SQLException;
import java.util.List;

import com.wpp.controller.HomeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpp.dao.FreeBoardDao;
import com.wpp.dao.UserDao;
import com.wpp.domain.FreeBoard;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface FreeBoardService {
	
	public int countboard(String searchOption, String keyword) throws Exception ;


	public List<FreeBoard> Viewlist(int start, int end, String searchOption, String keyword) throws Exception ;

	public List<FreeBoard> popboard() throws Exception ;

	public void create(FreeBoard vo) throws Exception ;

	public void uphit(int bnum) throws Exception;

	public String findByWriter(int bnum) ;


	@Transactional
	public Object read(int bnum) throws Exception;

	public FreeBoard detail(Integer bnum);


	public void update(FreeBoard vo) throws Exception;

	public void delete(int bnum) throws Exception ;

}
