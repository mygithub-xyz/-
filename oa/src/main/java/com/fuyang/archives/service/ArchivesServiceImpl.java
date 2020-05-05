package com.fuyang.archives.service;

import com.fuyang.archives.dao.ArchivesDao;
import com.fuyang.bean.Archives;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArchivesServiceImpl implements ArchivesService{
    @Autowired
    private ArchivesDao archivesDao;

    //保存一组数据
    @Transactional
    @Override
    public void save(List<Archives> list) {
        archivesDao.insertList(list);
    }
}
