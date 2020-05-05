package com.fuyang.notice.service;

import com.fuyang.bean.Forumpost;
import com.fuyang.bean.ForumpostExtends;

import java.util.List;

public interface ForumService {
    List<ForumpostExtends> queryForumpostList();

    void save(Forumpost forumpost);

    ForumpostExtends queryForumpostById(int forumid);
}
