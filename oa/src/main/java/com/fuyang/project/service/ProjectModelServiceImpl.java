package com.fuyang.project.service;
import com.fuyang.bean.ModuleExtends;
import com.fuyang.project.dao.ProjectModelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectModelServiceImpl implements ProjectModelService {
    @Autowired
    private ProjectModelDao projectModelDao;

    @Override
    public List<ModuleExtends> queryProjectModelList() {
        List<ModuleExtends> moduleExtends = projectModelDao.queryProjectModelList();
        return moduleExtends;
    }
}
