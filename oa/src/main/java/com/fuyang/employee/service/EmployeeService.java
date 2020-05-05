package com.fuyang.employee.service;

import com.fuyang.bean.Employee;
import com.fuyang.bean.EmployeeExtends;

import java.util.List;

public interface EmployeeService {
    List<Employee> queryEmployeeListLimit(int num);
    EmployeeExtends queryEmployeeByNameAndPass(String name, String pass);

    List<EmployeeExtends> queryEmployeeList();

    void save(Employee employee, int roleid);

    EmployeeExtends selectMenu(int id);
}
