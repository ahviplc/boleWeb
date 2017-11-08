package com.baorenai.bole.Utils;

import lombok.Getter;
import lombok.Setter;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * @project boleWeb
 * @auther baorenai
 * @create 2017/11/6星期一
 */
@Getter
@Setter
public class MailUtil {
    InternetAddress sendAddress = new InternetAddress();
    InternetAddress receiveAddress = new InternetAddress();

    //SendMail类的入参为用户的邮箱
    public static void sendMail(String address, File file) throws MessagingException, UnsupportedEncodingException {
        Properties properties = new Properties();
        properties.setProperty("mail.host", "smtp.163.com");
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties);
        session.setDebug(true);
        Transport transport = session.getTransport();
        transport.connect("smtp.163.com", "baorenai@u51.com", "Bob,0815");
        Message message = createMail(session, address, file);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();

    }

    public MailUtil() {
        System.out.println("请输入收件人邮箱！！");
    }

    public static void sendMail(String address) throws MessagingException {
        Properties properties = new Properties();
        properties.setProperty("mail.host", "smtp.163.com");
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties);
        session.setDebug(false);
        Transport transport = session.getTransport();
        transport.connect("smtp.163.com", "baorenai@u51.com", "Bob,0815");
        Message message = createWarnMail(session, address);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    //发送没有内容提醒邮件
    public static MimeMessage createWarnMail(Session session, String address) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("baorenai@u51.com"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(address));
        //   message.setRecipient(Message.RecipientType.CC,new InternetAddress("m13758274529@163.com"));
        message.setSubject("网易内推");
        message.setContent("你输入的岗位没有在招聘，或者是你的岗位信息输入有误。<br>你可以在<b><a href=\"http://hr.163.com\">  网易招聘  </a> </b>查看招聘信息，并将你想要投递的岗位+邮箱＋姓名+手机+工作地点（北上广杭）＋学历＋毕业时间，回复到此邮件。", "text/html;charset=UTF-8");
        return message;
    }

    //发送邮件，附件为职位信息
    public static MimeMessage createMail(Session session, String address, File file) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = new MimeMessage(session);
        //设置抄送人
        message.setFrom(new InternetAddress("baorenai@u51.com"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(address));
        //      message.setRecipient(Message.RecipientType.CC,new InternetAddress("m13758274529@163.com"));
        message.setSubject("网易内推");
        // message.setContent("这是发送邮件的内容吗?","text/html;charset=UTF-8"); //已经被下面覆盖了

        MimeBodyPart text = new MimeBodyPart();
        text.setContent("hi,附件为该岗位的相关信息，包含岗位描述、要求等信息。" +
                "<br><b> 请查收！</b><br> 如果没有合适的岗位，请到<b><a href=\"http://hr.163.com\">  网易招聘  </a> </b>查看招聘信息，并将你想要投递的岗位+邮箱＋姓名+手机+工作地点（北上广杭）＋学历＋毕业时间，回复到此邮件。 <br> <b>注意！！如果已经在该招聘网上投简历了，无法内推！", "text/html;charset=UTF-8");

        MimeBodyPart attach = new MimeBodyPart();
        // DataHandler dh = new DataHandler(new FileDataSource("C:\\Users\\hzbaorenai\\Desktop\\Code\\bole\\src\\data\\jobData\\jobData.txt"));
        DataHandler dh = new DataHandler(new FileDataSource(file));
//        attach.setDataHandler(dh);
        // attach.setFileName("what.txt");
        // attach.setFileName(dh.getName());
        attach.setFileName(MimeUtility.encodeText(dh.getName()));

        MimeMultipart mimeMultipart = new MimeMultipart();
        mimeMultipart.addBodyPart(text);
        mimeMultipart.addBodyPart(attach);
        mimeMultipart.setSubType("mixed");

        message.setContent(mimeMultipart);
        message.saveChanges();
        return message;
    }

/*    public static void main(String[] args) throws MessagingException {
        MailUtil.sendMail("baorenai@u51.com");
    }*/
}




