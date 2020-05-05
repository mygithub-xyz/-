package com.fuyang.baoxiao.dao;

import com.fuyang.bean.Expendituretype;

import java.util.List;

public interface BaoxiaoTypeDao {
    List<Expendituretype> selectTypeList();

    Expendituretype selectTypeById(int id);
}
