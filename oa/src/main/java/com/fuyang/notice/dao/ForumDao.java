package com.fuyang.notice.dao;

import com.fuyang.bean.Forumpost;
import com.fuyang.bean.ForumpostExtends;

import java.util.List;

public interface ForumDao {
    List<ForumpostExtends> selectForumpostList();

    void insert(Forumpost forumpost);

    ForumpostExtends selectForumpostById(int forumid);
}
