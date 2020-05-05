package com.fuyang.dept.controller;

import com.fuyang.bean.Dept;
import com.fuyang.dept.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/dept")
public class TestController {

    @Autowired
    private DeptService deptService;

    @RequestMapping("/getDept")
    @ResponseBody
    public List<Dept> queryAll(){
        return deptService.queryDeptList();
    }
}
