package com.fuyang.bean;

import java.util.List;

public class ForumpostExtends extends Forumpost{
    private EmployeeExtends employeeExtends;

    //单向一对多，从帖子到回复，一个帖子可以又多个回复
    private List<EvaluateExtends> list;

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
