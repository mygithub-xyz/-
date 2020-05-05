package com.fuyang.baoxiao.service;

import com.fuyang.baoxiao.dao.BaoxiaoDao;
import com.fuyang.baoxiao.dao.BaoxiaoReplyDao;
import com.fuyang.bean.Baoxiao;
import com.fuyang.bean.BaoxiaoExtends;
import com.fuyang.bean.Baoxiaoreply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BaoxiaoServiceImpl implements BaoxiaoService{
    @Autowired
    private BaoxiaoDao baoxiaoDao;
    @Autowired
    private BaoxiaoReplyDao baoxiaoReplyDao;

    @Override
    public List<BaoxiaoExtends> queryBaoxiaoByEid(int eid) {
        return baoxiaoDao.selectBaoxiaoByEid(eid);
    }

    //增加功能
    @Transactional
    @Override
    public void save(Baoxiao baoxiao) {
        baoxiaoDao.insert(baoxiao);
    }

    //按照状态查询数据
    @Override
    public List<BaoxiaoExtends> queryBaoxiaoByStatus(int status) {
        return baoxiaoDao.selectBaoxiaoByStatus(status);
    }

    //按照id查数据
    @Override
    public BaoxiaoExtends queryBaoxiaoById(String bxid) {
        return baoxiaoDao.selectBaoxiaoById(bxid);
    }

    //是驳回或者同意功能实现，修改报销信息，审批意见的录入
    @Transactional
    @Override
    public void check(Baoxiao baoxiao) {
        baoxiaoDao.update(baoxiao);

        if(baoxiao.getResult() != null && baoxiao.getResult().length() >0) {
            Baoxiaoreply reply = new Baoxiaoreply();
            reply.setContent(baoxiao.getResult());
            reply.setBaoxiaoFk(baoxiao.getBxid());
            reply.setReplytime(new Date());
            baoxiaoReplyDao.insert(reply);
        }
    }
}
