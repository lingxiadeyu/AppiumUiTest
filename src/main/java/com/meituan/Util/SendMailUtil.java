package com.meituan.Util;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/*
发送邮件
 */

public class SendMailUtil {
    ElementSource elementSource = new ElementSource();

    public void send(String content) throws MessagingException {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol","smtp");
        properties.setProperty("mail.smtp.auth","true");
        properties.setProperty("mail.smtp.host","smtp.sina.com");
        properties.setProperty("mail.smtp.port","25");

        //建立会话
        Session session = Session.getDefaultInstance(properties);
        //建立信息
        Message message = new MimeMessage(session);
        //设置发件人
        message.setFrom(new InternetAddress(elementSource.getElementsource("sender")));

        //获取收件人并设置收件人，收件人为多个
        String receiverlist = getreceiverlist();
        InternetAddress[] sendtolist = new InternetAddress().parse(receiverlist);
        message.setRecipients(MimeMessage.RecipientType.TO,sendtolist);

        //设置发送日期
        message.setSentDate(new Date());
        //设置主题
        message.setSubject("APPium自动化测试异常");
        //设置内容
        message.setText(content);
        //存储邮件信息
        message.saveChanges();

        //邮件服务器验证
        Transport tran = session.getTransport("smtp");
        tran.connect("smtp.sina.com",elementSource.getElementsource("senderusername"),elementSource.getElementsource("senderpassword"));

        tran.sendMessage(message,message.getAllRecipients());
        tran.close();
        System.out.println("发送成功");


    }

    //获取收件人信息
    public String getreceiverlist(){
        //从yaml文件中获取收件人信息并拼接返回
        StringBuffer receiverlist = new StringBuffer();
        String[] receiver = elementSource.getElementsource("receiver").split(";");
        int length = receiver.length;
        for (int i=0;i<length;i++){
            receiverlist.append(receiver[i]);
            if (i != length-1){
                receiverlist.append(",");
            }
        }
        return receiverlist.toString();

    }

    public static void main(String[] args) throws MessagingException {
        SendMailUtil sendMailUtil = new SendMailUtil();
//        String receiverlist = sendMailUtil.getreceiverlist();
//        System.out.println(receiverlist);

        sendMailUtil.send("异常问题");
    }

}
