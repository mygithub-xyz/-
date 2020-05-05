package com.fuyang.bean;

public class ProjectExtends extends Project  {
    //单向的，从项目到客户的，一对一关系，一个项目只属于一个客户
    private Customer customer;
    //单向的，从项目到项目经理的，一对一关系，一个项目只有一个项目经理
    private Employee employee;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
