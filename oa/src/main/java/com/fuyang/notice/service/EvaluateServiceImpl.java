package com.fuyang.notice.service;

import com.fuyang.bean.Evaluate;
import com.fuyang.notice.dao.EvaluateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EvaluateServiceImpl implements EvaluateService{
    @Autowired
    private EvaluateDao evaluateDao;

    //增加功能
    @Transactional
    @Override
    public void save(Evaluate evaluate) {
        evaluateDao.insert(evaluate);
    }
}
