package com.fuyang.notice.service;

import com.fuyang.bean.Msg;
import com.fuyang.bean.MsgExtends;
import com.fuyang.notice.dao.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{
    @Autowired
    private MessageDao messageDao;

    //按照用户id查发送人发送的消息
    @Override
    public List<MsgExtends> queryMessageListByEid(int eid) {
        return messageDao.selectMessageListByEid(eid);
    }

    //保存消息
    @Transactional
    @Override
    public void save(Msg msg) {
        messageDao.insert(msg);
    }
}
