package com.fuyang.position.dao;

import com.fuyang.bean.Position;
import java.util.List;
public interface PositionDao {
    List<Position> selectAll();
    Position selectPositionById(int id);
}