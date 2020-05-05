package com.fuyang.source.controller;
import com.fuyang.bean.Sources;
import com.fuyang.bean.SourcesExtends;
import com.fuyang.source.service.SourcesService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/source")
public class SourcesController {
    @Autowired
    private SourcesService sourcesService;
    @RequestMapping("/source-show")
    @ResponseBody
    public List<SourcesExtends> sourceShow() {
        return sourcesService.querySource();
    }
   //资源添加
    @RequestMapping("/pm-add")
    public String addSource(Sources sources){
        sourcesService.add(sources);
        return "redirect:/pm.jsp";
    }
    @RequestMapping("/getOneById")
    //展示要修改的资源
    public String selectSource(Model model,int id){
       Sources sources= sourcesService.selectSourceById(id);
       model.addAttribute("onesource",sources);
        return "/pm-edit.jsp";
    }
    @RequestMapping("/updateInfo")
    //资源修改
    public String editService(Sources sources){
        sourcesService.edit(sources);
        return "redirect:/pm.jsp";
    }
    //删除资源
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String,Boolean> deleteSource(int id){
        sourcesService.delete(id);
        Map<String,Boolean> map=new HashMap<>();
        map.put("success",true);
        return map;
    }
}