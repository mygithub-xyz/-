package com.fuyang.project.controller;

import com.fuyang.bean.Analysis;
import com.fuyang.bean.AnalysisExtends;
import com.fuyang.bean.Project;
import com.fuyang.project.service.ProjectBaseService;
import com.fuyang.project.service.ProjectNeedService;
import com.fuyang.util.FileDownLoadUtils;
import com.fuyang.util.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/project")
public class ProjectNeedController {

    @Autowired
    private ProjectBaseService projectBaseService;

    @Autowired
    private ProjectNeedService projectNeedService;

    @RequestMapping("/project-need-show")
    public String projectNeedShow(Model model){
        List<AnalysisExtends> aes = projectNeedService.queryProjectNeedList();
        model.addAttribute("analysises",aes);
        return "/project-need.jsp";
    }

    @RequestMapping("/project-need-project-show")
    @ResponseBody
    //mark标记，有可能是根据此标记查询没有需求分析的项目；还可能是查询所有的项目
    public List<Project>projectNeedProjectShow(int mark){
        return projectBaseService.queryProjectByMark(mark);
    }


    //上传和保存数据
    @RequestMapping("/project-need-saveInfo")
    public String projectNeedSaveInfo(Analysis analysis, MultipartFile files) {
        analysis.setAddtime(new Date());
        //上传文件处理
        if(files != null){
        //上传工具类
           File file = FileUploadUtils.fileUpload(files);
            analysis.setProname(file.getName());
        }
        projectNeedService.save(analysis);
        return "redirect:/project/project-need-show";
    }

    //下载文件的实现
    @RequestMapping("/project-need-file-download")
    public String projectNeedFileDownload(String filename, HttpServletResponse response)  {
        //下载工具类
        FileDownLoadUtils.fileDownLoad(response,filename);
        return "redirect:/project/project-need-show";
    }
}
