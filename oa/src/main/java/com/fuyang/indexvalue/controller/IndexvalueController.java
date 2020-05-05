package com.fuyang.indexvalue.controller;
import com.fuyang.bean.Indexvalue;
import com.fuyang.bean.IndexvalueExtends;
import com.fuyang.indexvalue.service.IndexvalueService;
import com.fuyang.util.FileUploadUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/indexvalue")
public class IndexvalueController {
    @Autowired
    private IndexvalueService indexvalueService;
    //指标的列表展示
    @RequestMapping("/indexvalue-show")
    public String indexvalueShow(Model model) {
        List<IndexvalueExtends> indexvalueExtends=indexvalueService.queryIndexvalue();
        model.addAttribute("indexvalues",indexvalueExtends);
        return "/indexvalue-base.jsp";
    }
    //指标的添加
    @RequestMapping("/saveIndexValueInfo")
    public String saveIndexValueInfo(@CookieValue("user")String user,Indexvalue indexvalue, MultipartFile files){
        //文件上传
        File file = FileUploadUtils.fileUpload(files);

        String[] split = user.split("_");
        int id=Integer.parseInt(split[split.length-1]);
        indexvalue.setEmpFk5(id);

        String path = file.getPath();
        indexvalue.setInFile(path);

         indexvalueService.sava(indexvalue);
        return "redirect:/indexvalue/indexvalue-show";
    }

}