package com.fuyang.bean;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(value = {"handler"})
public class AnalysisExtends extends Analysis  {
    //单向的一对一，从需求分析到项目，一个需求分析只属于一个项目
    private ProjectExtends project;
    private List<Module> modules;

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public ProjectExtends getProject() {
        return project;
    }

    public void setProject(ProjectExtends project) {
        this.project = project;
    }
}
