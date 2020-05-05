package com.fuyang.notice.controller;

import com.fuyang.bean.Evaluate;
import com.fuyang.notice.service.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/evaluate")
public class EvaluateController {

    @Autowired
    private EvaluateService evaluateService;

    @RequestMapping("/evaluate-add")
    public String evaluateAdd(Evaluate evaluate,@CookieValue("user")String user){
        //评论人
        int index = user.lastIndexOf("_");
        String value = user.substring(index+1);
        int eid = Integer.parseInt(value);
        evaluate.setEmpFk4(eid);

        evaluate.setEvatime(new Date());

        evaluateService.save(evaluate);

        return "redirect:/forum/forum-look?forumid="+evaluate.getForumFk();
    }
}
