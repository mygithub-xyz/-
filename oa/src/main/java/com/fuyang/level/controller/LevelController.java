package com.fuyang.level.controller;

import com.fuyang.bean.Level;
import com.fuyang.level.service.LevelService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequestMapping("/level")
public class LevelController {
    @Autowired
    private LevelService levelService;
    @RequestMapping("/getLevel")
    @ResponseBody
    public List<Level> selectOne(Integer id) {
        return levelService.queryAll();
    }

}