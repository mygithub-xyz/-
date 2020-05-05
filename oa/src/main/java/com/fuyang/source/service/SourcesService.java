package com.fuyang.source.service;
import com.fuyang.bean.Sources;
import com.fuyang.bean.SourcesExtends;

import java.util.List;
public interface SourcesService {
    List<SourcesExtends> querySource();

    void add(Sources sources);

    Sources selectSourceById(int id);

    void edit(Sources sources);

    void delete(int id);
}