package com.fuyang.project.controller;

import com.fuyang.bean.AnalysisExtends;
import com.fuyang.bean.FunctionExtends;
import com.fuyang.project.service.ProjectFunctionService;
import com.fuyang.project.service.ProjectNeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectFunctionController {
    @Autowired
    private ProjectNeedService projectNeedService;
    @Autowired
    private ProjectFunctionService projectFunctionService;

    @RequestMapping("/project-function-need-show")
    @ResponseBody
    public AnalysisExtends projectFunctionNeedShow(int pid){
        return projectNeedService.selectProjectNeedById(pid);
    }
    @RequestMapping("/project-function-show")
    public  String projectFunctionShow(Model model){
        List<FunctionExtends> functionExtends=projectFunctionService.queryProjectFunctionList();
        model.addAttribute("funs",functionExtends);
        return "/project-function.jsp";
    }
}
