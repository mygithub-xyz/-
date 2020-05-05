package com.fuyang.notice.controller;

import com.fuyang.bean.Email;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

@Controller
@RequestMapping("/email")
public class EmailController {

    @RequestMapping("/email-send")
    public String emailSend(Email email, MultipartFile files,@CookieValue("user")String user) throws IOException, MessagingException {
        File file = null;
        //接收页面送来的数据
        if(files != null){
            String originalFilename = files.getOriginalFilename();
            file = new File("C:\\Users\\zhonggong2\\Desktop\\upload",originalFilename);
            files.transferTo(file);
        }

        email.setSendtime(new Date());

        int index = user.lastIndexOf("_");
        String value = user.substring(index+1);
        int eid = Integer.parseInt(value);
        email.setEmpFk2(eid);

        //调用业务层、调用持久层把邮件数据保存到email表中去


//        System.out.println(email);
        //在程序中实现邮件的发送操作--代替在邮箱界面中的操作
        /*
        * 设置邮箱账号+授权码
        * 设置邮箱服务器
        * 设置以及其他参数
        *
        * 登录验证
        *
        * 封装一封邮件MimeMessge对象--保存发送的邮件的相关信息
        *
        * 从程序一方建立到邮箱的连接(实际上是建立和邮件服务器的连接)--session
        * 然后实现发送--发送对象Transport
        *
        * 释放资源
        * */
       final String sendP = "3129889878@qq.com";//被借用的账号
        final String code = "sciktljrbwucdcdh";

        String recvP = "paopao361@yeah.net";//接收方
        String server = "smtp.qq.com";

        Properties properties = new Properties();
        //协议
        properties.setProperty("mail.transport.protocol", "smtp");
        //服务器地址
        properties.setProperty("mail.smtp.host", server);
        //是否验证
        properties.setProperty("mail.smtp.auth", "true");
        /////////向上参数配置完成
        //创建验证器--在实现连接的时候，用验证器来实现登录验证
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                // 密码验证
                return new PasswordAuthentication(sendP, code);// 邮箱的授权码
            }
        };
        //建立连接
        Session session = Session.getInstance(properties,auth);

        session.setDebug(true);

        //封装邮件
        MimeMessage message = createMimeMessage(email,file,session,sendP,recvP);

        //传输对象
        Transport transport = session.getTransport();
        transport.connect(sendP, code);
        //发送邮件
        transport.sendMessage(message, message.getAllRecipients());//相当于在邮箱中点击发送邮件的按钮

        transport.close();
        return "/email.jsp";
    }

    private MimeMessage createMimeMessage(Email email, File file, Session session, String sendP, String recvP) throws UnsupportedEncodingException, MessagingException {
        //封装一封邮件
        //邮件对象
        MimeMessage message = new MimeMessage(session);
        //接收方--可以设置接收方的昵称
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(recvP, "老展", "UTF-8"));
        //发送方---可以设置昵称
        message.setFrom(new InternetAddress(sendP, "老包", "UTF-8"));
        //标题
        message.setSubject(email.getTitle(), "UTF-8");
        //内容：文本+附件(附件可能没有)
        //先设置文本，后设置附件
        // 创建多重消息
        Multipart multipart = new MimeMultipart();
        //保存文本和附件的对象BodyPart
        BodyPart messageBodyPart = new MimeBodyPart();
        if(email.getEmailcontent() != null && email.getEmailcontent().length() > 0) {
            messageBodyPart.setText(email.getEmailcontent());
            //在一个网站中注册之后，网站发送到邮箱中一个激活链接
//        messageBodyPart.setContent("<a target='_BLANK' href='http://www.baidu.com'>先生/女士您好，请点击此链接激活账号</a>","text/html;charset=utf-8");
        }else {
            messageBodyPart.setText("");
        }
        //把内容中的文本部分，添加到多重消息对象中
        multipart.addBodyPart(messageBodyPart);

        if(file != null) {
            //附件
            messageBodyPart = new MimeBodyPart();
            //读取附件
            DataSource source = new FileDataSource(file);
            //把附件保存到bodyPart对象
            messageBodyPart.setDataHandler(new DataHandler(source));
            //解决发送附件的中文乱码
            messageBodyPart.setFileName(MimeUtility.encodeText(file.getName()));

            //把bodyPart放入多重信息对象
            multipart.addBodyPart(messageBodyPart);
        }

        //邮件对象封装内容部分
        message.setContent(multipart);

        Date date = new Date(System.currentTimeMillis()+5000);
        message.setSentDate(date);

        message.saveChanges();

        return message;
    }
}
