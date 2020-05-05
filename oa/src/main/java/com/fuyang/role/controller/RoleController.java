package com.fuyang.role.controller;
import com.fuyang.bean.Role;
import com.fuyang.bean.RoleExtends;
import com.fuyang.role.service.RoleService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @RequestMapping("/getRole")
    @ResponseBody
    public List<Role> queryAll(){
        return roleService.queryAll(1);
    }
    @RequestMapping("/role-show")
    public String showRole(Model model){
        List<Role> roles = roleService.queryAll(0);
        model.addAttribute("roles",roles);
        return "/role.jsp";
    }
@RequestMapping("/showOneRole")
@ResponseBody
public RoleExtends showOneRole(int roleid){
    RoleExtends roles = roleService.queryRoleById(roleid);
    return roles;
}
@RequestMapping("/role-addRole")
public String addRole(Role role,String sourcesid){
        roleService.save(role,sourcesid);
        return "redirect:/role/role-show";
}
@RequestMapping("/role-edit")
public String editRole(Role role ,String sourcesid){
        roleService.edit(role,sourcesid);
    return "redirect:/role/role-show";
}

}