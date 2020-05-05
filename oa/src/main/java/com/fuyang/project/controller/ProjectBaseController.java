package com.fuyang.project.controller;

import com.fuyang.bean.Condition;
import com.fuyang.bean.Project;
import com.fuyang.bean.ProjectExtends;
import com.fuyang.project.service.ProjectBaseService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/project")
//项目展示
public class ProjectBaseController {
 @Autowired
 private ProjectBaseService projectBaseService;
 @RequestMapping("/project-show")
 @ResponseBody
 public PageInfo<ProjectExtends> projectShow(@RequestParam(defaultValue = "1") int currentPage ,Condition condition){
     String keyword = condition.getKeyword();
     condition.setKeyword("%"+keyword+"%");
     PageInfo<ProjectExtends> pageInfo =projectBaseService.queryProjectList(currentPage,condition);
     return pageInfo;
 }
 //项目添加
 @RequestMapping("/project-saveInfo")
 public ResponseEntity<Void> projectAddInfo(Project project){
     projectBaseService.addProject(project);
     return  ResponseEntity.ok(null);
 }
 //项目批量删除
 @RequestMapping("/project-delete")
 public ResponseEntity<Void> projectDelete(int[] ids){
     projectBaseService.deleteProject(ids);
     return  ResponseEntity.ok(null);
 }
    //项目的修改或者查看详情页面数据的查询
    @RequestMapping("/project-base-edit-show")
    @ResponseBody
    public ResponseEntity<ProjectExtends>projectBaseEditShow(int pid){
        ProjectExtends pe = projectBaseService.queryProjectById(pid);
        return ResponseEntity.ok(pe);
    }

    //修改功能
    @RequestMapping("/project-edit")
    public ResponseEntity<Void>projectEdit(Project project){
        projectBaseService.edit(project);
        return ResponseEntity.ok(null);
    }

}