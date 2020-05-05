package com.fuyang.project.service;

import com.fuyang.bean.Function;
import com.fuyang.bean.FunctionExtends;

import java.util.List;

public interface ProjectFunctionService {
    public List<FunctionExtends> queryProjectFunctionList();

    List<Function> queryFunctionByMid(int mid);
}
