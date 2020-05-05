package com.fuyang.indexvalue.service;
import com.fuyang.bean.Indexvalue;
import com.fuyang.bean.IndexvalueExtends;

import java.util.List;
public interface IndexvalueService {
    List<IndexvalueExtends> queryIndexvalue();

    void sava(Indexvalue indexvalue);
}