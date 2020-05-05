package com.fuyang.project.controller;

import com.fuyang.bean.Analysis;
import com.fuyang.bean.AnalysisExtends;
import com.fuyang.bean.ModuleExtends;
import com.fuyang.bean.Project;
import com.fuyang.project.service.ProjectBaseService;
import com.fuyang.project.service.ProjectModelService;
import com.fuyang.project.service.ProjectNeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/project")
public class ProjectModelController {

    @Autowired
    private ProjectModelService projectModelService;
    @RequestMapping("/project-model-show")
    public String projectNeedShow(Model model){
        List<ModuleExtends> aes = projectModelService.queryProjectModelList();
        model.addAttribute("models",aes);
        return "/project-model.jsp";
    }
}
