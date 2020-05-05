package com.fuyang.bean;

import java.util.List;

public class BaoxiaoExtends extends Baoxiao{
    //单向的一对一，从报销到报销人，一个报销单只属于一个人
    private EmployeeExtends employeeExtends;
    //当前报销单是什么类型的报销单
    private Expendituretype expendituretype;

    //往次被驳回的意见
    //单向的一对多，从报销到报销驳回意见，一各报销可以有多个意见
    private List<Baoxiaoreply> baoxiaoreplyList;

    public EmployeeExtends getEmployeeExtends() {
        return employeeExtends;
    }

    public void setEmployeeExtends(EmployeeExtends employeeExtends) {
        this.employeeExtends = employeeExtends;
    }

    public Expendituretype getExpendituretype() {
        return expendituretype;
    }

    public void setExpendituretype(Expendituretype expendituretype) {
        this.expendituretype = expendituretype;
    }

    public List<Baoxiaoreply> getBaoxiaoreplyList() {
        return baoxiaoreplyList;
    }

    public void setBaoxiaoreplyList(List<Baoxiaoreply> baoxiaoreplyList) {
        this.baoxiaoreplyList = baoxiaoreplyList;
    }
}
