package com.fuyang.baoxiao.dao;

import com.fuyang.bean.Baoxiaoreply;

import java.util.List;

public interface BaoxiaoReplyDao {

    List<Baoxiaoreply> selectReplyByBxid(String bxid);

    void insert(Baoxiaoreply reply);
}
