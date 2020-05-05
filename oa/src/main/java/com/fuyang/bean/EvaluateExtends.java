package com.fuyang.bean;

import javax.validation.constraints.Max;
import java.util.List;

public class EvaluateExtends extends Evaluate{

    //回帖人信息
    private EmployeeExtends employeeExtends;

    //二级评论集合,一个一级评论可以有多个二级评论
    private List<EvaluateExtends>list;

    public EmployeeExtends getEmployeeExtends() {
        return employeeExtends;
    }

    public void setEmployeeExtends(EmployeeExtends employeeExtends) {
        this.employeeExtends = employeeExtends;
    }

    public List<EvaluateExtends> getList() {
        return list;
    }

    public void setList(List<EvaluateExtends> list) {
        this.list = list;
    }
}
