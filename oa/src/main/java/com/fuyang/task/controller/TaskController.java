package com.fuyang.task.controller;

import com.fuyang.bean.Function;
import com.fuyang.bean.Task;
import com.fuyang.project.service.ProjectFunctionService;
import com.fuyang.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private ProjectFunctionService projectFunctionService;
    @Autowired
    private TaskService taskService;

    //任务添加页面功能的展示请求
    @RequestMapping("/task-add-function-show")
    @ResponseBody
    public List<Function> taskAddFunctionShow(int mid){
        return projectFunctionService.queryFunctionByMid(mid);
    }
    //任务的添加功能
    @RequestMapping("/task-addInfo")
    //@CookieValue的功能是从cookie中获取指定键值对，然后把值赋给形参
    public String taskAddInfo(Task task, @CookieValue("user")String user){
        //任务的状态：0未开始、1未完成、2已完成
        task.setStatus(0);
        //分配任务的人---项目经理---就是当前登录用户---获取当前登录用户的id
        //从cookie中获取到user=xxxxxxx键值对，值截取到当前登录用户id
        int index = user.lastIndexOf("_");
        String num = user.substring(index + 1);
        int eid = Integer.parseInt(num);

        task.setEmpFk(eid);
        taskService.save(task);
        return "redirect:/task.jsp";
    }
}
