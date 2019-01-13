package com.wpp.service;

import com.wpp.controller.HomeController;
import com.wpp.dao.FreeBoardDao;
import com.wpp.dao.UserDao;
import com.wpp.domain.FreeBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class FreeBoardServiceImpl implements FreeBoardService{
    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

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


    @Transactional
    public Object read(int bnum) throws Exception {
        Object object = null;

        //조회수 올리기
        freeboardDao.uphit(bnum);

        //게시물 내용
        object = freeboardDao.read(bnum);

        //트랜잭션 테스트를 위한 Exception 발생
        if( object == null) {
            throw new RuntimeException();
        }

        return object;
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
