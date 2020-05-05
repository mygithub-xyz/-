package com.fuyang.baoxiao.service;

import com.fuyang.baoxiao.dao.BaoxiaoTypeDao;
import com.fuyang.bean.Expendituretype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.List;

@Service
public class BaoxiaoTypeServiceImpl implements BaoxiaoTypeService{
    @Autowired
    private BaoxiaoTypeDao baoxiaoTypeDao;

    //显示报销类型
    @Override
    public List<Expendituretype> queryTypeList() {
        return baoxiaoTypeDao.selectTypeList();
    }
}
