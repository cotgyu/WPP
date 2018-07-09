package com.wpp.dao;

import java.util.List;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.wpp.domain.NoticeReply;



public class NoticeReplyDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	private DataSource dataSource;
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	public List<NoticeReply> list(Integer bnum) {
		return sqlSession.selectList("NoticeReplyMapper.listReply", bnum);
	}
	
	public void create(NoticeReply vo) {
		sqlSession.insert("NoticeReplyMapper.insertReply", vo);
		//sqlSession.update("replyMapper.setreGroup");
	}
	
    public void update(NoticeReply vo) throws Exception {
        sqlSession.update("NoticeReplyMapper.updateReply",vo);
    }
   
    public void delete(Integer rnum)  {
    	sqlSession.delete("NoticeReplyMapper.deleteReply", rnum);
    }
    
	public NoticeReply detail(Integer rnum) {
		return sqlSession.selectOne("NoticeReplyMapper.replymodifyview", rnum);
	}
	
	public void createcomment(NoticeReply vo) {
		sqlSession.insert("NoticeReplyMapper.insertReplyComment",vo);
	}

	public void stepshape(NoticeReply vo){
		sqlSession.update("NoticeReplyMapper.replyStepShape", vo);	
	}
	
	public void create_setgroup(NoticeReply vo) {
		sqlSession.update("NoticeReplyMapper.create_setgroup",vo);
		
	}
}