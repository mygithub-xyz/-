package com.fuyang.employee.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuyang.bean.Employee;
import com.fuyang.bean.EmployeeExtends;
import com.fuyang.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JedisPool jedisPool;
    @RequestMapping("/project-add-employee-show")
    @ResponseBody
    public ResponseEntity<List<Employee>>projectAddEmployeeShow(){
        List<Employee> emps = employeeService.queryEmployeeListLimit(20);
        return ResponseEntity.ok(emps);
    }
    //登录功能
    @RequestMapping("/login")
    public String login(String name, String pass, Model model, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        //登录成功和失败
        EmployeeExtends employee = employeeService.queryEmployeeByNameAndPass(name,pass);

        if (employee != null){
            //登录成功
            //小米商城把当前登录用户信息保存到session
            //修正把当前登录用户信息保存到redis
            Jedis resource = jedisPool.getResource();
            String key = UUID.randomUUID().toString().replaceAll("-","")+"_"+employee.getEid();
            ObjectMapper om=new ObjectMapper();
            String value = om.writeValueAsString(employee);
            resource.set(key,value);
            //缺陷有效时间慢慢失效，解决方案采用过滤器，每来一个请求，就在过滤器中重新设定时间，也可以使用拦截器，在拦截器中设置有效时间
            resource.expire(key,1800);
            resource.close();
            //假如把key放入到Cookie中，把Cookie响应给用户，当用户再次请求的时候，Cookie被又送回服务器端了
            Cookie co = new Cookie("user",key);
            //cookie的作用范围升级成为项目路径
            co.setPath(request.getContextPath());
            response.addCookie(co);
            return "/index.jsp";
        }else {
            //登录失败
            model.addAttribute("msg","用户名或者密码不正确");
            return "/login.jsp";
        }
    }
    //登出操作
    @RequestMapping("/logout")
    public String logout(@CookieValue("user")String user, HttpSession session,HttpServletRequest request,HttpServletResponse response){
        //登录用户的信息从redis库中删除
        Jedis resource = jedisPool.getResource();
        resource.expire(user,0);
        resource.close();
        //让cookie立即失效
        Cookie cookie =new Cookie("user",null);
        cookie.setMaxAge(0);
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);
        //session立即失效
        session.invalidate();
        return "redirect:/login.jsp";
    }
    //人员管理展示
    @RequestMapping("/employee-user")
    public String userShowList(Model model){
        List<EmployeeExtends> employeeExtends= employeeService.queryEmployeeList();
        model.addAttribute("employees",employeeExtends);
        return "/user.jsp";
    }
    //人员管理的添加
    //员工/用户信息保存
    @RequestMapping("/employee-addEmp")
    public String employeeAddEmp(Employee employee,int roleid){
        employeeService.save(employee,roleid);
        return "redirect:/employee/employee-user";
    }
    ///////////////////////////不同用户展示不同的列表//////////////////////////////////
    @RequestMapping("/getSource-menu")
    @ResponseBody
    public EmployeeExtends menuShow(@CookieValue("user")String user){
        String[] split = user.split("_");
        int id=Integer.parseInt(split[split.length-1]);
         EmployeeExtends employeeExtends=employeeService.selectMenu(id);
        return employeeExtends;
    }
}
