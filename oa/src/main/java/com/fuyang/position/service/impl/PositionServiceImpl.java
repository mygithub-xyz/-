package com.fuyang.position.service.impl;

import com.fuyang.bean.Position;
import com.fuyang.position.dao.PositionDao;
import com.fuyang.position.service.PositionService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;
@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionDao positionDao;
    @Override
    public List<Position> queryAll() {
        return this.positionDao.selectAll();
    }





}