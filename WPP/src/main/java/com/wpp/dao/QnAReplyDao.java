package com.wpp.dao;

import java.util.List;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.wpp.domain.QnAReply;




public class QnAReplyDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	private DataSource dataSource;
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	public List<QnAReply> list(Integer bnum) {
		return sqlSession.selectList("QnAReplyMapper.listReply", bnum);
	}
	
	public void create(QnAReply vo) {
		sqlSession.insert("QnAReplyMapper.insertReply", vo);
	}
	
    public void update(QnAReply vo) throws Exception {
        sqlSession.update("QnAReplyMapper.updateReply",vo);
    }
   
    public void delete(Integer rnum)  {
    	sqlSession.delete("QnAReplyMapper.deleteReply", rnum);
    }
    
	public QnAReply detail(Integer rnum) {
		return sqlSession.selectOne("QnAReplyMapper.replymodifyview", rnum);
	}
	
	public void createcomment(QnAReply vo) {
		sqlSession.insert("QnAReplyMapper.insertReplyComment",vo);
	}

	public void stepshape(QnAReply vo){
		sqlSession.update("QnAReplyMapper.replyStepShape", vo);	
	}
	
	public void create_setgroup(QnAReply vo) {
		sqlSession.update("QnAReplyMapper.create_setgroup",vo);
	}
}