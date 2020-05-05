package com.fuyang.project.dao;

import com.fuyang.bean.Condition;
import com.fuyang.bean.ModuleExtends;
import com.fuyang.bean.Project;
import com.fuyang.bean.ProjectExtends;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectBaseDao {
    List<ProjectExtends> queryProjectList(Condition condition);

    void addProject(Project project);

    void deleteProject(@Param("ids") int[] ids);

    ProjectExtends queryProjectById(int pid);
    void update(Project project);

    List<Project> selectProjectByMark(@Param("mark") int mark);

}
