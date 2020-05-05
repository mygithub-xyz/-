package com.fuyang.indexvalue.service;
import com.fuyang.bean.Indexvalue;
import com.fuyang.bean.IndexvalueExtends;
import com.fuyang.indexvalue.dao.IndexvalueDao;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class IndexvalueServiceImpl implements IndexvalueService {
    @Autowired
    private IndexvalueDao indexvalueDao;

    @Override
    public List<IndexvalueExtends> queryIndexvalue() {
        return indexvalueDao.selectIndexvalue();
    }

    @Override
    @Transactional
    public void sava(Indexvalue indexvalue) {
        indexvalueDao.add(indexvalue);
    }
}