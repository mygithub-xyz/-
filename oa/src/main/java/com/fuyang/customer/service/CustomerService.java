package com.fuyang.customer.service;

import com.fuyang.bean.Condition;
import com.fuyang.bean.Customer;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CustomerService {
    List<Customer> selectCustomers();

    PageInfo<Customer> selectCustomers(int currentPage);

    void save(Customer customer);

    Customer selectCustomerById(int id);

    void editCustomerById(Customer customer);

    void batchDelete(int[] idArr);

    PageInfo<Customer> queryCustomerList(int currentPage, Condition condition);

    List<Customer> queryCustomerByIds(int[] idArray);

    List<Customer> queryCustomerListLimit(int i, String s);
}
