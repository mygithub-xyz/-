package com.fuyang.indexvalue.dao;
import com.fuyang.bean.Indexvalue;
import com.fuyang.bean.IndexvalueExtends;
import java.util.List;

public interface IndexvalueDao {

    List<IndexvalueExtends> selectIndexvalue();

    void add(Indexvalue indexvalue);
}