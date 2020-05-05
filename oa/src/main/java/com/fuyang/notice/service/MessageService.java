package com.fuyang.notice.service;

import com.fuyang.bean.Msg;
import com.fuyang.bean.MsgExtends;

import java.util.List;

public interface MessageService {
    List<MsgExtends> queryMessageListByEid(int eid);

    void save(Msg msg);
}
