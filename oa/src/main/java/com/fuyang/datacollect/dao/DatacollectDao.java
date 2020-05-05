package com.fuyang.datacollect.dao;
import com.fuyang.bean.Datacollect;

import java.util.List;

public interface DatacollectDao {
    Datacollect queryById(Integer daid);

    List<Datacollect> queryDatacollectList();

    List<Datacollect> queryPng(String dacname);
}