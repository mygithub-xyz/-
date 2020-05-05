package com.fuyang.notice.controller;

import com.fuyang.bean.Msg;
import com.fuyang.bean.MsgExtends;
import com.fuyang.bean.MsgJob;
import com.fuyang.notice.service.MessageService;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    //列表
    @RequestMapping("/message-show")
    public String messageShow(Model model,@CookieValue("user")String user){
        int index = user.lastIndexOf("_");
        String value = user.substring(index+1);
        int eid = Integer.parseInt(value);
        List<MsgExtends> msgs = messageService.queryMessageListByEid(eid);

        model.addAttribute("msgs",msgs);
        return "/message-give.jsp";
    }

    //消息的保存--推送消息
    @RequestMapping("/message-saveMsg")
    public String messageSaveMsg(Msg msg,@CookieValue("user")String user) throws SchedulerException {
        int index = user.lastIndexOf("_");
        String value = user.substring(index+1);
        int eid = Integer.parseInt(value);
        msg.setSendp(eid);

//        messageService.save(msg);//保存功能不在此处实现了，是由作业调度当时间来临时，触发保存功能

        //作业调度
        //容器--注册中心，用来把触发器和调度者实现关联
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //调度者---调任务，实现功能
        //HelloJob类中封装这调度任务，触发时所触发的功能
//        JobDetail job = JobBuilder.newJob(HelloJob.class)
//                .withIdentity("myJob", "group1")
//                .build();

        //触发消息的保存功能
        JobDetail job = JobBuilder.newJob(MsgJob.class)
                //给调度者命名和分组
                .withIdentity("myJob", "group1")
                .build();

        //设置触发时间
        Date startTime = new Date(System.currentTimeMillis() + 10000);
        //触发器---触发，时间来临时，触发调度者执行功能
        Trigger trigger = TriggerBuilder.newTrigger()
                //给触发器分组和命名
                .withIdentity("myTrigger", "group1")
                //一次触发，不会重复触发
                .withSchedule(SimpleScheduleBuilder.simpleSchedule())
                //设置触发时间
                .startAt(startTime)
                .build();

        //设置把消息数据msg对象从当前类，送到MsgJob类中
        job.getJobDataMap().put("msg",msg);
        //触发器和作业调度的关联
        scheduler.scheduleJob(job, trigger);

        //启动作业调度，后台线程
        if(!scheduler.isShutdown()){
            scheduler.start();
        }

        return "redirect:/message/message-show";
    }
}
