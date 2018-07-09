package com.wpp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.wpp.domain.Notice;



public class NoticeDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	private DataSource dataSource;
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	
	public List<Notice> Viewlist(int start, int end, String searchOption, String keyword) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
	    map.put("start", start);
	    map.put("end", end);
	    
		return sqlSession.selectList("NoticeMapper.viewlist", map);
	}
	
	public void create(Notice vo)  throws Exception{
		 sqlSession.insert("NoticeMapper.insert",vo);
	}
	
	public void uphit(int bnum) throws Exception{
		sqlSession.update("NoticeMapper.uphit", bnum);
	}
	
	public Notice read(int bnum) throws Exception{
		return sqlSession.selectOne("NoticeMapper.view",bnum);
	}
	
	public void update(Notice vo) throws Exception{
		sqlSession.update("NoticeMapper.updateArticle", vo);
	}
	
	public void delete(int bnum) throws Exception{
		sqlSession.delete("NoticeMapper.delete", bnum);
	}
	
	public int countboard(String searchOption, String keyword) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		
		return sqlSession.selectOne("NoticeMapper.countboard", map);
	}
	
	public List<Notice> popboard() throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		
		return sqlSession.selectList("NoticeMapper.popboard", map);
	}
	
	public Notice detail(Integer bnum) {
		return sqlSession.selectOne("NoticeMapper.modifyview", bnum);
	}
	
	public String findByWriter(int bnum) {
		return sqlSession.selectOne("NoticeMapper.findByWriter", bnum);
	}

	
}
