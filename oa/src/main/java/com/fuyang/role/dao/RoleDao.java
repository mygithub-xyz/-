package com.fuyang.role.dao;

import com.fuyang.bean.EmpRole;
import com.fuyang.bean.Role;
import com.fuyang.bean.RoleExtends;
import org.apache.ibatis.annotations.Param;

import java.util.List;
public interface RoleDao {
    List<Role> selectAll(@Param("i") int i);

    void insert(EmpRole er);

    RoleExtends selectRoleById(int roleid);
    RoleExtends selectRoleEid(int eid);
    void insertRole(Role role);

    void update(Role role);
}