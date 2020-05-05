package com.fuyang.level.service.impl;

import com.fuyang.bean.Level;
import com.fuyang.level.dao.LevelDao;
import com.fuyang.level.service.LevelService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

 @Service
public class LevelServiceImpl implements LevelService {
   @Autowired
  private LevelDao levelDao;
    @Override
    public List<Level> queryAll() {
    return levelDao.selectAll();
    }
}