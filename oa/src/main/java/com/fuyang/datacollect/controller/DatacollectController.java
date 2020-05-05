package com.fuyang.datacollect.controller;
import com.fuyang.bean.Datacollect;
import com.fuyang.datacollect.service.DatacollectService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequestMapping("/datacollect")
public class DatacollectController {
    @Autowired
    private DatacollectService datacollectService;
    //对标企业下拉选择展示
    @RequestMapping("/getInfoGroup")
    @ResponseBody
    public List<Datacollect> selectGroup() {
        return datacollectService.selectDatacollectList();
    }
    //根据下拉选id展示具体对标企业信息
    @RequestMapping("/getOneByCname")
    @ResponseBody
    public Datacollect selectOneByCname(int daid) {
        return datacollectService.selectDatacollectById(daid);
    }
    //标杆公司月季度营业额分析查询
    @RequestMapping("/datacollect-showPng")
    @ResponseBody
    public List<Datacollect> showPng(String name ){
        return datacollectService.selectPng(name);
    }
}