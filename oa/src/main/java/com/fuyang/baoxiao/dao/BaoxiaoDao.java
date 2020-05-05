package com.fuyang.baoxiao.dao;

import com.fuyang.bean.Baoxiao;
import com.fuyang.bean.BaoxiaoExtends;

import java.util.List;

public interface BaoxiaoDao {
    List<BaoxiaoExtends> selectBaoxiaoByEid(int eid);

    void insert(Baoxiao baoxiao);

    List<BaoxiaoExtends> selectBaoxiaoByStatus(int status);

    BaoxiaoExtends selectBaoxiaoById(String bxid);

    void update(Baoxiao baoxiao);
}
