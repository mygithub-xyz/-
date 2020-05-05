package com.fuyang.baoxiao.controller;

import com.fuyang.baoxiao.service.BaoxiaoService;
import com.fuyang.baoxiao.service.BaoxiaoTypeService;
import com.fuyang.bean.Baoxiao;
import com.fuyang.bean.BaoxiaoExtends;
import com.fuyang.bean.Expendituretype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/baoxiao")
public class BaoxiaoController {

    @Autowired
    private BaoxiaoService baoxiaoService;
    @Autowired
    private BaoxiaoTypeService baoxiaoTypeService;

    //显示我的报销单---当前登录用户的
    @RequestMapping("/mybaoxiao-show")
    public String myBaoxiaoShow(Model model, @CookieValue("user")String user){
        int index = user.lastIndexOf("_");
        String value = user.substring(index+1);
        int eid = Integer.parseInt(value);
        List<BaoxiaoExtends>bes = baoxiaoService.queryBaoxiaoByEid(eid);
        model.addAttribute("bes",bes);
        return "/mybaoxiao-base.jsp";
    }


    //报销添加页面的展示
    @RequestMapping("/mybaoxiao-add-show")
    public String mybaoxiaoAddShow(Model model){
        List<Expendituretype> types = baoxiaoTypeService.queryTypeList();
        //报销类型的数据送回
        model.addAttribute("expendType",types);
        return "/mybaoxiao-add.jsp";
    }

    //添加报销的功能
    @RequestMapping("/mybaoxiao-saveInfo")
    public String mybaoxiaoSaveInfo(Baoxiao baoxiao, @CookieValue("user")String user){
        int index = user.lastIndexOf("_");
        String value = user.substring(index+1);
        int eid = Integer.parseInt(value);

        baoxiao.setEmpFk(eid);

        baoxiaoService.save(baoxiao);
        return "redirect:/baoxiao/mybaoxiao-show";
    }
    //我的报销修改页面的展示
    @RequestMapping("/mybaoxiao-edit-show")
    public String mybaoxiaoEditShow(String bxid,Model model){
        List<Expendituretype> types = baoxiaoTypeService.queryTypeList();
        BaoxiaoExtends baoxiao = baoxiaoService.queryBaoxiaoById(bxid);
        model.addAttribute("baoxiao",baoxiao);
        model.addAttribute("types",types);
        return "/mybaoxiao-edit.jsp";
    }
    //我的报销单的修改
    @RequestMapping("/mybaoxiao-edit")
    public String mybaoxiaoEdit(Baoxiao baoxiao,@CookieValue("user")String user){
        int index = user.lastIndexOf("_");
        String value = user.substring(index+1);
        int eid = Integer.parseInt(value);

        baoxiao.setEmpFk(eid);
        baoxiaoService.check(baoxiao);
        return "redirect:/baoxiao/mybaoxiao-show";
    }
    ////////////////////////////////////////////////////////////////////////////////
    //报销审批列表展示
    @RequestMapping("/udobaoxiao")
    public String udoBaoxiao(int status,Model model){
        List<BaoxiaoExtends>bes = baoxiaoService.queryBaoxiaoByStatus(status);
        model.addAttribute("bes",bes);
        return "/baoxiao-task.jsp";
    }

    //报销审批页面的展示
    @RequestMapping("/baoxiao-task-edit-show")
    public String baoxiaoTaskEditShow(String bxid,Model model){
        List<Expendituretype> types = baoxiaoTypeService.queryTypeList();
        BaoxiaoExtends baoxiao = baoxiaoService.queryBaoxiaoById(bxid);
        model.addAttribute("baoxiao",baoxiao);
        model.addAttribute("types",types);
        return "/baoxiao-task-edit.jsp";
    }

    //报销审批操作
    @RequestMapping("/baoxiao-task-edit")
    public String baoxiaoTaskEdit(Baoxiao baoxiao){
        baoxiaoService.check(baoxiao);
        return "redirect:/baoxiao/udobaoxiao?status=0";
    }
}
