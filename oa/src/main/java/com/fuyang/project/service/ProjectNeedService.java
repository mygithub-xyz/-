package com.fuyang.project.service;

import com.fuyang.bean.Analysis;
import com.fuyang.bean.AnalysisExtends;

import java.util.List;

public interface ProjectNeedService {
    void save(Analysis analysis);

    List<AnalysisExtends> queryProjectNeedList();

    AnalysisExtends selectProjectNeedById(int pid);
}
