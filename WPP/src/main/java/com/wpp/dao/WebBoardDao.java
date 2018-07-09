package com.wpp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wpp.domain.WebBoard;



@Repository
public class WebBoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private DataSource dataSource;
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public List<WebBoard> Viewlist(int start, int end, String searchOption, String keyword) throws Exception{	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
	    map.put("start", start);
	    map.put("end", end);
	    
		return sqlSession.selectList("WebBoardMapper.viewlist", map);
	}
		
	public void create(WebBoard vo)  throws Exception{
		 sqlSession.insert("WebBoardMapper.insert",vo);
	}
	
	public void uphit(int bnum) throws Exception{
		sqlSession.update("WebBoardMapper.uphit", bnum);
	}

	public WebBoard read(int bnum) throws Exception{
		return sqlSession.selectOne("WebBoardMapper.view",bnum);
	}
	
	public void update(WebBoard vo) throws Exception{
		sqlSession.update("WebBoardMapper.updateArticle", vo);
	}
	
	public void delete(int bnum) throws Exception{
		sqlSession.delete("WebBoardMapper.delete", bnum);
	}

	public int countboard(String searchOption, String keyword) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		
		return sqlSession.selectOne("WebBoardMapper.countboard", map);
	}
	
	public List<WebBoard> popboard() throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		
		return sqlSession.selectList("WebBoardMapper.popboard", map);
	}
	
	public WebBoard detail(Integer bnum) {
			return sqlSession.selectOne("WebBoardMapper.modifyview", bnum);
	}
	
	public String userlist() {
		return sqlSession.selectOne("WebBoardMapper.userlist");
	}
	
	public String findByWriter(int bnum) {
		return sqlSession.selectOne("WebBoardMapper.findBywriter",bnum);
	}
	
	public List<WebBoard> recentboard() {
		Map<String, String> map = new HashMap<String, String>();
		
		return sqlSession.selectList("WebBoardMapper.recentboard", map);
	}
}
