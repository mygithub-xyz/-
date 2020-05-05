package com.fuyang.dept.service;

import com.fuyang.bean.Dept;
import com.fuyang.dept.dao.DeptDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService{
    @Autowired
    private DeptDao deptDao;

    @Override
    public List<Dept> queryDeptList() {
        return deptDao.selectDeptList();
    }
}
