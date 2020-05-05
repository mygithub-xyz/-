package com.fuyang.datacollect.service;
import com.fuyang.bean.Datacollect;
import com.fuyang.datacollect.dao.DatacollectDao;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class DatacollectServiceImpl implements DatacollectService {
    @Autowired
    private DatacollectDao datacollectDao;

    @Override
    public List<Datacollect> selectDatacollectList() {
        return datacollectDao.queryDatacollectList();
    }

    @Override
    public Datacollect selectDatacollectById(int daid) {
        return datacollectDao.queryById(daid);
    }

    @Override
    public List<Datacollect> selectPng(String dacname) {
        return datacollectDao.queryPng(dacname);
    }
}