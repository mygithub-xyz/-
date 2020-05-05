package com.fuyang.role.service;

import com.fuyang.bean.Role;
import com.fuyang.bean.RoleExtends;

import java.util.List;

public interface RoleService {
    List<Role> queryAll(int i);

    RoleExtends queryRoleById(int roleid);

    void edit(Role role, String sourcesid);

    void save(Role role, String sourcesid);
}