package com.fuyang.project.dao;

import com.fuyang.bean.Function;
import com.fuyang.bean.FunctionExtends;

import java.util.List;

public interface ProjectFunctionDao {
    public List<FunctionExtends> selectProjectFunctionList();

    List<Function> selectFunctionByMid(int mid);
}
