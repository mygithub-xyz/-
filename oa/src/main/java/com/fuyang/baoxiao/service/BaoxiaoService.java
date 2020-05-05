package com.fuyang.baoxiao.service;

import com.fuyang.bean.Baoxiao;
import com.fuyang.bean.BaoxiaoExtends;

import java.util.List;

public interface BaoxiaoService {
    List<BaoxiaoExtends> queryBaoxiaoByEid(int eid);

    void save(Baoxiao baoxiao);

    List<BaoxiaoExtends> queryBaoxiaoByStatus(int status);

    BaoxiaoExtends queryBaoxiaoById(String bxid);

    void check(Baoxiao baoxiao);
}
