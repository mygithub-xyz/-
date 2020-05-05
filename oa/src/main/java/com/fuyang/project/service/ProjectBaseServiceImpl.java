package com.fuyang.project.service;

import com.fuyang.bean.Condition;
import com.fuyang.bean.Project;
import com.fuyang.bean.ProjectExtends;
import com.fuyang.project.dao.ProjectBaseDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectBaseServiceImpl implements ProjectBaseService {
    @Autowired
    private ProjectBaseDao projectBaseDao;
    @Override
    public PageInfo<ProjectExtends> queryProjectList(int currentPage, Condition condition) {
        PageHelper.startPage(currentPage,3);
       List<ProjectExtends> projectExtends=projectBaseDao.queryProjectList(condition);
       PageInfo<ProjectExtends> pi =new PageInfo<>(projectExtends,5);
       return pi;
    }

    @Transactional
    @Override
    public void addProject(Project project ) {
        projectBaseDao.addProject(project);
    }
    @Transactional
    @Override
    public void deleteProject(int[] ids) {
        projectBaseDao.deleteProject(ids);
    }

    @Override
    public ProjectExtends queryProjectById(int pid) {
        return projectBaseDao.queryProjectById(pid);
    }
   @Transactional
    @Override
    public void edit(Project project) {
         projectBaseDao.update(project);
    }
    //按照Mark标记查询项目，查询的结果是：或者是没有需求分析的项目；或者是不论是否存在需求分析的所有项目
    //Mark为0表示没有需求分析的项目，Mark不等于0表示不论是否有需求分析的所有项目
    @Override
    public List<Project> queryProjectByMark(int mark) {
        return projectBaseDao.selectProjectByMark(mark);
    }
}
