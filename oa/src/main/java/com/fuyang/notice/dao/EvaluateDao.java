package com.fuyang.notice.dao;

import com.fuyang.bean.Evaluate;
import com.fuyang.bean.EvaluateExtends;

import java.util.List;

public interface EvaluateDao {
    public List<EvaluateExtends>selectEvaluteByFid(int fid);

    public List<EvaluateExtends>selectEvaluateByEid(int eid);

    void insert(Evaluate evaluate);
}
