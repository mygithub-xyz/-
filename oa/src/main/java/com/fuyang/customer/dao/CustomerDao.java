package com.fuyang.customer.dao;

import com.fuyang.bean.Condition;
import com.fuyang.bean.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerDao {
    public List<Customer> selectCustomers();

    void insert(Customer customer);

    Customer selectCustomerById(int id);

    void editCustomerById(Customer customer);

    void batchDelete(@Param("ids") int[] idArr);

    List<Customer> selectCustomerListByCondition(Condition condition);

    List<Customer> selectCustomerByIds(@Param("ids") int[] idArray);

    List<Customer> queryCustomerListLimit(@Param("i") int i, @Param("name") String name);
}
