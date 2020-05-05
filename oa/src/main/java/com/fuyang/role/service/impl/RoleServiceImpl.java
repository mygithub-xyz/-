package com.fuyang.role.service.impl;

import com.fuyang.bean.Role;
import com.fuyang.bean.RoleExtends;
import com.fuyang.bean.RoleSources;
import com.fuyang.role.dao.RoleDao;
import com.fuyang.role.service.RoleService;
import com.fuyang.source.dao.SourcesDao;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private SourcesDao sourcesDao;
    @Autowired
    private RoleDao roleDao;
    @Override
    public List<Role> queryAll(int i) {
        return roleDao.selectAll(i);
    }

    @Override
    public RoleExtends queryRoleById(int roleid) {
     RoleExtends roles = roleDao.selectRoleById(roleid);
        return roles;
    }
    @Transactional
    @Override
    public void save(Role role, String sourcesid) {
        //往角色表中插入数据
       roleDao.insertRole(role);
       //往中间表插入数据
        String[] split = sourcesid.split(",");
        Integer roleid = role.getRoleid();
        List<RoleSources> list=new ArrayList<>();
        for (String sp : split) {
            RoleSources rs=new RoleSources();
            rs.setRoleid(roleid);
            rs.setSid(Integer.parseInt(sp));
            rs.setRsdis(role.getRolename()+"有"+rs.getSid()+"的权限");
            list.add(rs);
        }
        sourcesDao.batchInsert(list);
    }

    @Transactional
    @Override
    public void edit(Role role, String sourcesid) {
     //修改角色信息
        roleDao.update(role);
     //修改中间表权限信息
        Integer roleid = role.getRoleid();
        sourcesDao.deleteByRoleid(roleid);
        String[] split = sourcesid.split(",");
        List<RoleSources> list=new ArrayList<>();
        for (String sp : split) {
            RoleSources rs=new RoleSources();
            rs.setRoleid(roleid);
            rs.setSid(Integer.parseInt(sp));
            rs.setRsdis(role.getRolename()+"有"+rs.getSid()+"的权限");
            list.add(rs);
        }
        sourcesDao.batchInsert(list);
    }


}