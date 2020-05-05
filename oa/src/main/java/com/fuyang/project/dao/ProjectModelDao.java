package com.fuyang.project.dao;

import com.fuyang.bean.Module;
import com.fuyang.bean.ModuleExtends;

import java.util.List;

public interface ProjectModelDao {

    List<ModuleExtends> queryProjectModelList();

     List<Module> selectModuleByAid(int aid);
     ModuleExtends selectModuleById(int id);
}
