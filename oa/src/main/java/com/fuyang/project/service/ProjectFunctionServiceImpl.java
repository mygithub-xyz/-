package com.fuyang.project.service;

import com.fuyang.bean.Function;
import com.fuyang.bean.FunctionExtends;
import com.fuyang.project.dao.ProjectFunctionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjectFunctionServiceImpl implements ProjectFunctionService {
   @Autowired
    private ProjectFunctionDao projectFunctionDao;
    @Override
    public List<FunctionExtends> queryProjectFunctionList() {
        return projectFunctionDao.selectProjectFunctionList();
    }

    @Override
    public List<Function> queryFunctionByMid(int mid) {
        return projectFunctionDao.selectFunctionByMid(mid);
    }
}
