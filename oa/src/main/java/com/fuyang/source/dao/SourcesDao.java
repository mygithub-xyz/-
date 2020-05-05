package com.fuyang.source.dao;
import com.fuyang.bean.RoleSources;
import com.fuyang.bean.Sources;
import com.fuyang.bean.SourcesExtends;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SourcesDao {

    List<SourcesExtends> selectSource(int id);
    List<RoleSources> selectRoleSource(int roleid);
     void batchInsert(@Param("list") List<RoleSources> roleSources);
     void deleteByRoleid(int roleid);

    void insert(Sources sources);

    Sources querySourceById(int id);

    void update(Sources sources);

    void deleteById(int id);

    List<SourcesExtends> selectSourcesByRid(@Param("roleid") Integer roleid,@Param("i") int i);
}