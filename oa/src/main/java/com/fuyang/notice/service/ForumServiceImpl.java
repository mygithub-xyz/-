package com.fuyang.notice.service;

import com.fuyang.bean.Forumpost;
import com.fuyang.bean.ForumpostExtends;
import com.fuyang.notice.dao.ForumDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ForumServiceImpl implements ForumService{

    @Autowired
    private ForumDao forumDao;

    //帖子列表
    @Override
    public List<ForumpostExtends> queryForumpostList() {
        return forumDao.selectForumpostList();
    }

    //保存功能
    @Transactional
    @Override
    public void save(Forumpost forumpost) {
        forumDao.insert(forumpost);
    }

    //按照主键查询
    @Override
    public ForumpostExtends queryForumpostById(int forumid) {
        ForumpostExtends fe = forumDao.selectForumpostById(forumid);
        return fe;
    }
}
