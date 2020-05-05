package com.fuyang.employee.service;

import com.fuyang.bean.*;
import com.fuyang.employee.dao.EmployeeDao;
import com.fuyang.role.dao.RoleDao;
import com.fuyang.source.dao.SourcesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private SourcesDao sourcesDao;
    //查询员工列表，但是有限制
    @Override
    public List<Employee> queryEmployeeListLimit(int num) {
        return employeeDao.selectEmployeeListLimit(num);
    }

    @Override
    public EmployeeExtends queryEmployeeByNameAndPass(String name, String pass) {
        //在数据库中保存的密码，通常是密文形式的，不是明文形式的；那么密码是经过加密处理的，
        //一般加密算法是不可逆的(由密码生成密文之后，不能再由密文返回原密码)
        //如果我们表中保存的密码是加密处理的，那么即使输入的密码是正确的，当把密码传到数据库中实现验证时，此时也验证不成功
        //此时该如何验证？
        //先在此处对密码进行加密，然后把用户名和加密的结果送到数据库，在数据库中验证
        //还可以先通过用户名把数据查回来，获取到数据库表中密文，再此处再把页面送来的密码进行加密，然后匹配密文和加密之后字符串

        EmployeeExtends ee = employeeDao.selectEmployeeByName(name);
        if(ee != null){
            String password = ee.getPassword();//获取到密码的密文
            //pass在此处应该有加密处理
            if(!password.equals(pass)){
                return null;
            }
        }
        return ee;
    }

    @Override
    public List<EmployeeExtends> queryEmployeeList() {
        return employeeDao.selectEmployeeList();
    }
    //保存员工信息和中间表(员工和角色中间表)信息
    @Override
    @Transactional
    public void save(Employee employee, int roleid) {
            //员工表数据保存；中间表数据保存
            employeeDao.insert(employee);

            EmpRole er = new EmpRole();
            er.setEmpFk(employee.getEid());
            er.setRoleFk(roleid);
            er.setErdis(employee.getEname() + "的角色");

            roleDao.insert(er);
        }

    @Override
    public EmployeeExtends selectMenu(int id) {
    EmployeeExtends employeeExtends = employeeDao.selectEmployeeByEid(id);
        RoleExtends role = employeeExtends.getRoleExtends();
        Integer roleid = role.getRoleid();
        //获取到的是父id为1的资源
        List<SourcesExtends> sourcesList = sourcesDao.selectSourcesByRid(roleid, 1);
        //由上一级找到下一级
        sourcesList = change(sourcesList, roleid);
        //给角色设置上一级资源
        role.setSourcesExtendsList(sourcesList);
        //给用户设置角色
        employeeExtends.setRoleExtends(role);
        return employeeExtends;
    }
    private List<SourcesExtends> change(List<SourcesExtends> sourcesList, Integer roleid) {
        if(sourcesList != null && sourcesList.size() > 0){
            for(SourcesExtends se : sourcesList){
                //当前资源id，上一层资源的id，通过这个id找下一级
                Integer pid = se.getId();
                List<SourcesExtends> children = sourcesDao.selectSourcesByRid(roleid, pid);
                se.setChildren(children);
            }
        }
        return sourcesList;
    }
}
