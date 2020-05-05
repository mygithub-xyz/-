package com.fuyang.archives.dao;

import com.fuyang.bean.Archives;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArchivesDao {
    void insertList(@Param("list") List<Archives> list);
}
