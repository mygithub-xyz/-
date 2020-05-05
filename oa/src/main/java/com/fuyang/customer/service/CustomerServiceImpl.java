package com.fuyang.customer.service;

import com.fuyang.bean.Condition;
import com.fuyang.bean.Customer;
import com.fuyang.customer.dao.CustomerDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.fuyang.bean.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService{
 @Autowired
 private CustomerDao customerDao;
    @Override
    public List<Customer> selectCustomers() {
        return customerDao.selectCustomers();
    }

    @Override
    public PageInfo<Customer> selectCustomers(int currentPage) {
        //以下顺序不能改变
        //第一个参数是当前页码，第二个参数是每页的条数
        PageHelper.startPage(currentPage,3);
        List<Customer> customers = customerDao.selectCustomers();
        //第一个参数是查询的结果，第二个参数是页面显示几个页码数字
        PageInfo<Customer>pi = new PageInfo<>(customers,5);
        return pi;
    }

    @Override
    @Transactional
    public void save(Customer customer) {

        customerDao.insert(customer);
    }

    @Override
    public Customer selectCustomerById(int id) {
        return customerDao.selectCustomerById(id);
    }
    @Transactional
    @Override
    public void editCustomerById(Customer customer) {
        customerDao.editCustomerById(customer);
    }
    @Transactional
    @Override
    public void batchDelete(int[] idArr) {
        customerDao.batchDelete(idArr);
    }

    @Override
    public PageInfo<Customer> queryCustomerList(int currentPage, Condition condition) {
        PageHelper.startPage(currentPage,3);
        List<Customer> customers = customerDao.selectCustomerListByCondition(condition);
        PageInfo<Customer>pi = new PageInfo<>(customers,5);
        return pi;
    }

    @Override
    public List<Customer> queryCustomerByIds(int[] idArray) {
        return customerDao.selectCustomerByIds(idArray);
    }

    @Override
    public List<Customer> queryCustomerListLimit(int i, String s) {
        return customerDao.queryCustomerListLimit( i,s);
    }
}
