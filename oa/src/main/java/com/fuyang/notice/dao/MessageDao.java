package com.fuyang.notice.dao;

import com.fuyang.bean.Msg;
import com.fuyang.bean.MsgExtends;

import java.util.List;

public interface MessageDao {
    List<MsgExtends> selectMessageListByEid(int eid);

    void insert(Msg msg);
}
