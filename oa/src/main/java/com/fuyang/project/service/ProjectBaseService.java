package com.fuyang.project.service;

import com.fuyang.bean.Condition;
import com.fuyang.bean.Project;
import com.fuyang.bean.ProjectExtends;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProjectBaseService {
    PageInfo<ProjectExtends> queryProjectList(int currentPage, Condition condition);

    void addProject(Project project);

    void deleteProject(int[] ids);

    ProjectExtends queryProjectById(int pid);

    void edit(Project project);
    List<Project> queryProjectByMark(int mark);
}
