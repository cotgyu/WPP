package com.wpp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.wpp.domain.QnA;




public class QnADao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	private DataSource dataSource;
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	
	public List<QnA> Viewlist(int start, int end, String searchOption, String keyword) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
	    map.put("start", start);
	    map.put("end", end);
	    
		return sqlSession.selectList("QnAMapper.viewlist", map);
	}
	
	public void create(QnA vo)  throws Exception{
		 sqlSession.insert("QnAMapper.insert",vo);
	}
	
	
	public void uphit(int bnum) throws Exception{
		sqlSession.update("QnAMapper.uphit", bnum);
	}
	
	public QnA read(int bnum) throws Exception{
		return sqlSession.selectOne("QnAMapper.view",bnum);
	}
	
	public void update(QnA vo) throws Exception{
		sqlSession.update("QnAMapper.updateArticle", vo);
	}
	
	public void delete(int bnum) throws Exception{
		sqlSession.delete("QnAMapper.delete", bnum);
	}
	
	public int countboard(String searchOption, String keyword) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		
		return sqlSession.selectOne("QnAMapper.countboard", map);
	}
	
	public List<QnA> popboard() throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		
		return sqlSession.selectList("QnAMapper.popboard", map);
	}
	
	public QnA detail(Integer bnum) {
		return sqlSession.selectOne("QnAMapper.modifyview", bnum);
	}
	
	public String findByWriter(int bnum) {
		return sqlSession.selectOne("QnAMapper.findByWriter", bnum);
	}
	public void setgroup(QnA vo) {
		sqlSession.update("QnAMapper.setgroup", vo);
	}

	
}
