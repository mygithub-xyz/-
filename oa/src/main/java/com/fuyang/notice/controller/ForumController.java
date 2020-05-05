package com.fuyang.notice.controller;

import com.fuyang.bean.Forumpost;
import com.fuyang.bean.ForumpostExtends;
import com.fuyang.notice.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/forum")
public class ForumController {

    @Autowired
    private ForumService forumService;

    //帖子的列表显示
    @RequestMapping("/forum-show")
    public String forumShow(Model model){
        List<ForumpostExtends>forums = forumService.queryForumpostList();
        model.addAttribute("forums",forums);
        return "/forum.jsp";
    }

    //发帖保存
    @RequestMapping("/forum-add")
    public String forumAdd(Forumpost forumpost, @CookieValue("user")String user){
        //发帖人
        int index = user.lastIndexOf("_");
        String value = user.substring(index+1);
        int eid = Integer.parseInt(value);
        forumpost.setEmpFk3(eid);

        //发帖时间
        forumpost.setCreatetime(new Date());

        forumService.save(forumpost);
        return "redirect:/forum/forum-show";
    }

    //进入帖子详情页
    @RequestMapping("/forum-look")
    public String forumLook(int forumid,Model model){
        //帖子对象中保存帖子信息和回复信息
        ForumpostExtends fe = forumService.queryForumpostById(forumid);
        model.addAttribute("forum",fe);
        return "/forum-reply.jsp";
    }
}
