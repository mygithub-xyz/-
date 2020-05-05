package com.fuyang.datacollect.service;
import com.fuyang.bean.Datacollect;

import java.util.List;
public interface DatacollectService {
    List<Datacollect> selectDatacollectList();

    Datacollect selectDatacollectById(int daid);

    List<Datacollect> selectPng(String dacname);
}