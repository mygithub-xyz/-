package com.fuyang.bean;

public class MsgExtends extends Msg{
    //接收人
    private EmployeeExtends employeeExtends;

    public EmployeeExtends getEmployeeExtends() {
        return employeeExtends;
    }

    public void setEmployeeExtends(EmployeeExtends employeeExtends) {
        this.employeeExtends = employeeExtends;
    }
}
