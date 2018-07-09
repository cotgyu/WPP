package com.wpp.dao;

import java.util.List;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wpp.domain.WebReply;



@Repository
public class WebReplyDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private DataSource dataSource;
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<WebReply> list(Integer bnum) {
		return sqlSession.selectList("WebReplyMapper.listReply", bnum);
	}
	
	public void create(WebReply vo) {
		sqlSession.insert("WebReplyMapper.insertReply", vo);
		//sqlSession.update("replyMapper.setreGroup");
	}
	
    public void update(WebReply vo) throws Exception {
        sqlSession.update("WebReplyMapper.updateReply",vo);
    }
   
    public void delete(Integer rnum)  {
    	sqlSession.delete("WebReplyMapper.deleteReply", rnum);
    }
    
	public WebReply detail(Integer rnum) {
		return sqlSession.selectOne("WebReplyMapper.replymodifyview", rnum);
	}
	
	public void createcomment(WebReply vo) {
		sqlSession.insert("WebReplyMapper.insertReplyComment",vo);
	}

	public void stepshape(WebReply vo){
		sqlSession.update("WebReplyMapper.replyStepShape", vo);	
	}
	
	public void create_setgroup(WebReply vo) {
		sqlSession.update("WebReplyMapper.create_setgroup", vo);
	}
}