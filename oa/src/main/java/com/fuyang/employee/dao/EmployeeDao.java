package com.fuyang.employee.dao;

import com.fuyang.bean.Employee;
import com.fuyang.bean.EmployeeExtends;

import java.util.List;

public interface EmployeeDao {
    List<Employee> selectEmployeeListLimit(int num);

    EmployeeExtends selectEmployeeByName(String name);
    EmployeeExtends selectEmployeeByEid(int eid);

    List<EmployeeExtends> selectEmployeeList();

    void insert(Employee employee);
}
