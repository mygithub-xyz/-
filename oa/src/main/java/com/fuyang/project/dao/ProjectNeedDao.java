package com.fuyang.project.dao;

import com.fuyang.bean.Analysis;
import com.fuyang.bean.AnalysisExtends;

import java.util.List;

public interface ProjectNeedDao {
    void insert(Analysis analysis);

    List<AnalysisExtends> selectProjectNeedList();
    AnalysisExtends selectProjectNeedById(Integer id);
}
