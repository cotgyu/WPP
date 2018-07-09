package com.wpp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.wpp.domain.FreeBoard;



public class FreeBoardDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	private DataSource dataSource;
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	
	public List<FreeBoard> Viewlist(int start, int end, String searchOption, String keyword) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
	    map.put("start", start);
	    map.put("end", end);
	    
		return sqlSession.selectList("FreeBoardMapper.viewlist", map);
	}
	
	
	public void create(FreeBoard vo)  throws Exception{
		 sqlSession.insert("FreeBoardMapper.insert",vo);
	}
	
	
	public void uphit(int bnum) throws Exception{
		sqlSession.update("FreeBoardMapper.uphit", bnum);
	}
	
	
	public FreeBoard read(int bnum) throws Exception{
		return sqlSession.selectOne("FreeBoardMapper.view",bnum);
	}
	
	
	public void update(FreeBoard vo) throws Exception{
		sqlSession.update("FreeBoardMapper.updateArticle", vo);
	}
	
	
	public void delete(int bnum) throws Exception{
		sqlSession.delete("FreeBoardMapper.delete", bnum);
		
	}
	
	public int countboard(String searchOption, String keyword) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		
		return sqlSession.selectOne("FreeBoardMapper.countboard", map);
	}
	
	public List<FreeBoard> popboard() throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		
		return sqlSession.selectList("FreeBoardMapper.popboard", map);
	}
	
	public FreeBoard detail(Integer bnum) {
		return sqlSession.selectOne("FreeBoardMapper.detail", bnum);
	}
	
	public String findByWriter(int bnum) {
		return sqlSession.selectOne("FreeBoardMapper.findByWriter", bnum);
	}

	
}
