package com.duty.util;

import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.duty.config.MailConfig;

public class MailUtil {
    private static final String HOST = MailConfig.host;
    private static final Integer PORT = MailConfig.port;
    private static final String USERNAME = MailConfig.userName;
    private static final String PASSWORD = MailConfig.passWord;
    private static final String emailForm = MailConfig.emailForm;
    private static final String timeout = MailConfig.timeout;
    private static final String personal = MailConfig.personal;
    private static JavaMailSenderImpl mailSender = createMailSender();
    /**
     * 邮件发送器
     *
     * @return 配置好的工具
     */
    private static JavaMailSenderImpl createMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(HOST);
        sender.setPort(PORT);
        sender.setUsername(USERNAME);
        sender.setPassword(PASSWORD);
        sender.setDefaultEncoding("Utf-8");
        Properties p = new Properties();
       // p.setProperty("mail.smtp.timeout", timeout);
        //p.setProperty("mail.smtp.auth", "false");
        p.put("mail.smtp.auth", "true");//一定要引号引起来
        p.put("mail.smtp.timeout", "25000");
        p.put("mail.smtp.localhost", MailConfig.yuming);  
        //p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        //p.put("mail.smtp.socketFactory.fallback", "false");
        sender.setJavaMailProperties(p);
        return sender;
    }

    /**
     * 发送邮件
     *
     * @param to 接受人
     * @param subject 主题
     * @param html 发送内容
     * @throws MessagingException 异常
     * @throws UnsupportedEncodingException 异常
     */
    public static void sendMail(String to, String subject, String html) throws MessagingException,UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        
        //mimeMessage.addRecipients(MimeMessage.RecipientType.CC, InternetAddress.parse("crenmiao@163.com"));
        
        
        // 设置utf-8或GBK编码，否则邮件会有乱码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(emailForm, personal);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(html, true);
        mailSender.send(mimeMessage);
    }
    
    
    public static void sendMailByGmail(String toUser,String title,String content){
    	
    	TimeZone.setDefault(TimeZone.getTimeZone("PST"));  
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("PST"));  
        //System.out.println("--------------------星期-------------"+cal.get(Calendar.DAY_OF_WEEK));
        //boolean isWeekendSend = false;
        if(PropertyUtil.getProperty("IsWeekendSend").equals("1")){//为1周末要发送
        	

	          Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	    	  final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	    	  // Get a Properties object
	    	  Properties props = System.getProperties();
	    	  props.setProperty("mail.smtp.host", HOST);
	    	  props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
	    	  props.setProperty("mail.smtp.socketFactory.fallback", "false");
	    	  props.setProperty("mail.smtp.port", "465");
	    	  props.setProperty("mail.smtp.socketFactory.port", "465");
	    	  props.put("mail.smtp.auth", "true");
	    	  final String username = USERNAME;
	    	  final String password = PASSWORD;
	    	  Session session = Session.getDefaultInstance(props, new Authenticator(){
	    	      protected PasswordAuthentication getPasswordAuthentication() {
	    	          return new PasswordAuthentication(username, password);
	    	      }});
	    	 
	    	       // -- Create a new message --
	    	  Message msg = new MimeMessage(session);
	    	  try {
	    	  // -- Set the FROM and TO fields --
	    	  msg.setFrom(new InternetAddress("stocklidage@gmail.com"));
	    	  
				msg.setRecipients(Message.RecipientType.TO, 
				    InternetAddress.parse(toUser,false));
			
	    	  msg.setSubject(title);
	    	  
	    	  String sendContent = PropertyUtil.getProperty("sentContent");
	    	  String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	    	  sendContent = sendContent.replace("${date}", today);
	    	  sendContent = sendContent.replace("${data}", content);
//	    	  content = new SimpleDateFormat("yyyy-MM-dd").format(new Date())+" Recommended stocks:"+"</br>["+content+"]";
	    	  msg.setContent(sendContent,"text/html;charset=UTF-8");
	    	  msg.setSentDate(new Date());
	    	  Transport.send(msg);
	    	  } catch (Exception e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		} 
	    	  System.out.println("Message sent.");
        }else{//需要判断周末是否发送
	        if(!(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)){

		          Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		    	  final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		    	  // Get a Properties object
		    	  Properties props = System.getProperties();
		    	  props.setProperty("mail.smtp.host", HOST);
		    	  props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		    	  props.setProperty("mail.smtp.socketFactory.fallback", "false");
		    	  props.setProperty("mail.smtp.port", "465");
		    	  props.setProperty("mail.smtp.socketFactory.port", "465");
		    	  props.put("mail.smtp.auth", "true");
		    	  final String username = USERNAME;
		    	  final String password = PASSWORD;
		    	  Session session = Session.getDefaultInstance(props, new Authenticator(){
		    	      protected PasswordAuthentication getPasswordAuthentication() {
		    	          return new PasswordAuthentication(username, password);
		    	      }});
		    	 
		    	       // -- Create a new message --
		    	  Message msg = new MimeMessage(session);
		    	  try {
		    	  // -- Set the FROM and TO fields --
		    	  msg.setFrom(new InternetAddress("stocklidage@gmail.com"));
		    	  
					msg.setRecipients(Message.RecipientType.TO, 
					    InternetAddress.parse(toUser,false));
				
		    	  msg.setSubject(title);
		    	  
		    	  String sendContent = PropertyUtil.getProperty("sentContent");
		    	  String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		    	  sendContent = sendContent.replace("${date}", today);
		    	  sendContent = sendContent.replace("${data}", content);
//		    	  content = new SimpleDateFormat("yyyy-MM-dd").format(new Date())+" Recommended stocks:"+"</br>["+content+"]";
		    	  msg.setContent(sendContent,"text/html;charset=UTF-8");
		    	  msg.setSentDate(new Date());
		    	  Transport.send(msg);
		    	  } catch (Exception e) {
		  			// TODO Auto-generated catch block
		  			e.printStackTrace();
		  		} 
		    	  System.out.println("Message sent.");
		    }
        }
    }
    
    
    
}