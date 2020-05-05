package com.fuyang.project.service;

import com.fuyang.bean.Analysis;
import com.fuyang.bean.AnalysisExtends;
import com.fuyang.project.dao.ProjectNeedDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectNeedServiceImpl implements ProjectNeedService{
    @Autowired
    private ProjectNeedDao projectNeedDao;

    //增加功能
    @Transactional
    @Override
    public void save(Analysis analysis) {
        projectNeedDao.insert(analysis);
    }

    //需求分析列表展示
    @Override
    public List<AnalysisExtends> queryProjectNeedList() {
        return projectNeedDao.selectProjectNeedList();
    }

    @Override
    public AnalysisExtends selectProjectNeedById(int id) {
        return projectNeedDao.selectProjectNeedById(id);
    }
}
