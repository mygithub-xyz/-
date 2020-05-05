package com.fuyang.position.controller;

import com.fuyang.bean.Position;
import com.fuyang.position.service.PositionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * (Position)表控制层
 *
 * @author makejava
 * @since 2020-03-23 21:16:02
 */
@Controller
@RequestMapping("/position")
public class PositionController {
    @Autowired
    private PositionService positionService;
    @RequestMapping("/getPosition")
    @ResponseBody
    public List<Position> selectOne() {
        List<Position> positions = positionService.queryAll();
        return  positions;
    }
}