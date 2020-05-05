package com.fuyang.bean;

import com.mysql.jdbc.Driver;
import com.fuyang.notice.service.MessageService;
import com.fuyang.notice.service.MessageServiceImpl;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MsgJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //保存功能。。。。。
//        MessageService ms = new MessageServiceImpl();
//        ms.save(msg);

        //在controller一侧把数据放入到了map集合中，本测通过cotext获取
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        Msg msg = (Msg) jobDataMap.get("msg");
        Connection conn = null;
        PreparedStatement pstat = null;
        try {
            //原生态的jdbc方案实现保存
            //驱动注册
            DriverManager.registerDriver(new Driver());
            //建立连接
            conn = DriverManager.getConnection("jdbc:mysql:///crmpro?characterEncoding=utf8", "root", "");
            //设置SQL
            String sql = "insert into msg(sendp,recvp,msgcontent,msgtime) values(?,?,?,?)";
            //定义容器
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, msg.getSendp());
            pstat.setInt(2, msg.getRecvp());
            pstat.setString(3, msg.getMsgcontent());
            pstat.setDate(4, new Date(System.currentTimeMillis()));
            //发送SQL进行执行
            pstat.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                //释放资源等
                conn.close();
                pstat.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
