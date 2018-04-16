package duty;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.Element;

import com.duty.config.CommonConfigItem;
import com.duty.config.MailConfig;
import com.duty.util.FileUtil;
import com.duty.util.MailUtil;
import com.duty.util.PathUtil;
import com.duty.util.PropertyUtil;

import au.com.bytecode.opencsv.CSVReader;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// huangshaowei 
//		String filePath = "e:\\2018-04-05_list.csv";
//		
//		
//		String path = "D://workspace/new/sfj/WebRoot/config/commonconfig.xml";
//		Document doc = FileUtil.loadXmlFile(path);
//		List<Element> eles = doc.getRootElement().elements();  
// 
//		for(Element e : eles){  
//	        System.out.println(e.attributeValue("id")); 
//	    }  
//		try {
//			MailUtil.sendMail("573245570@qq.com", "注册通知", "恭喜您注册成功！");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//MailUtil.sendMailByGmail();
		
//		String host = "smtp.gmail.com";  
//	    String from = "stocklidage@gmail.com";  
//	    String pass = "web@PWD2018";  
//	    Properties props = System.getProperties();  
//	    props.put("mail.smtp.starttls.enable", "true"); // 在本行添加  
//	    props.put("mail.smtp.host", host);  
//	    props.put("mail.smtp.user", from);  
//	    props.put("mail.smtp.password", pass);  
//	    props.put("mail.smtp.port", "587");  
//	    props.put("mail.smtp.auth", "true");  
//	 
//	    
//	    String[] to = {"573245570@qq.com"}; // 在本行添加  
//	 
//	    Session session = Session.getDefaultInstance(props, null);  
//	    MimeMessage message = new MimeMessage(session);  
//	    try {
//			message.setFrom(new InternetAddress(from));
//		
//	 
//	    InternetAddress[] toAddress = new InternetAddress[to.length];  
//	 
//	    // 获取地址的array  
//	    for( int i=0; i < to.length; i++ ) { // 从while循环更改而成  
//	        toAddress[i] = new InternetAddress(to[i]);  
//	    }  
//	    System.out.println(Message.RecipientType.TO);  
//	 
//	    for( int i=0; i < toAddress.length; i++) { // 从while循环更改而成  
//	        message.addRecipient(Message.RecipientType.TO, toAddress[i]);  
//	    } 
//	    
//	    message.setSubject("sending in a group");  
//	    message.setText("Welcome to JavaMail");  
//	    Transport transport = session.getTransport("smtp");  
//	    transport.connect(host, from, pass);  
//	    transport.sendMessage(message, message.getAllRecipients());  
//	    transport.close();  
//	 
//	    } catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	    
	    
		
//		String t = DigestUtils.md5Hex("123456");
//		System.out.println(t);
		
//		File file = new File("e:\\2018-04-05_list.csv");  
//        FileReader fReader = null;
//        CSVReader csvReader = null;
//		try {
//			fReader = new FileReader(file);
//		 
//	        csvReader = new CSVReader(fReader);  
//	        List<String[]> list = csvReader.readAll();  
////	        for(String s : list.get(list.size()-1))  {
////		        System.out.print(s);
////	        }
//	        for(String[] ss : list){  
//	            for(String s : ss)  
//	                if(null != s && !s.equals(""))  
//	                    System.out.print(s );  
//	            System.out.println();  
//	        }  
//	        
//	        
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}finally{
//			try {
//				if (fReader != null) {  
//					fReader.close();
//				}
//				if(csvReader!=null){
//					csvReader.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			} 
//		}
//		try {    
//            BufferedReader reader = new BufferedReader(new FileReader(filePath));//换成你的文件名   
//            //reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉   
//            String line = null;    
//            while((line=reader.readLine())!=null){    
//                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分   
//                    
//                String last = item[item.length-1];//这就是你要的数据了   
//                //int value = Integer.parseInt(last);//如果是数值，可以转化为数值   
//                System.out.println(last);    
//            }    
//        } catch (Exception e) {    
//            e.printStackTrace();    
//        }    
		
		
		
		// 1.创建一个程序与邮件服务器会话对象 Session
//		 Properties props = new Properties();
//		 props.put("mail.smtp.host", "smtp.163.com");
//		 props.put("mail.smtp.auth", "true");
//		 props.put("mail.smtp.localhost", "localhost");
//		 // 验证账号及密码，密码需要是第三方授权码
//		 Authenticator auth = new Authenticator() {
//			 public PasswordAuthentication getPasswordAuthentication(){
//			    
//				 return new PasswordAuthentication("crenmiao@163.com", "crm123");
//			 }
//		 };
//		 Session session = Session.getInstance(props, auth);
//
//		// 2.创建一个Message，它相当于是邮件内容
//		Message message = new MimeMessage(session);
//		// 设置发送者
//		try {
//		message.setFrom(new InternetAddress("crenmiao@163.com"));
//		// 设置发送方式与接收者
//		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("573245570@qq.com"));
//		// 设置主题
//		message.setSubject("股票推荐");
//		// 设置内容
//		
//			message.setContent("<h1>Stock,apple</h1>", "text/html;charset=utf-8");
//		
//
//		// 3.创建 Transport用于将邮件发送
//		Transport.send(message);
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
		
		
//		
//		Properties props = new Properties();
//		props.put("mail.smtp.host", "smtp.163.com");
//
//	
//
//
//		String mailAddress = "crenmiao@163.com";
//		String mailPwd = "crm123";
//		
//		final String f_mailAddress = mailAddress;
//		final String f_mailPwd = mailPwd;
//		Authenticator auth = new Authenticator()
//		{
//			public PasswordAuthentication getPasswordAuthentication()
//			{
//				return new PasswordAuthentication(f_mailAddress, f_mailPwd);
//			}
//		};
//		
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.localhost", "localhost");
//
//
//			MimeMessage message = null;
//			Session session = Session.getInstance(props, auth);
//			try {
//				InternetAddress addressFrom = new InternetAddress(f_mailAddress, "Lawfile");
//			message = new MimeMessage(session);
//			message.setFrom(addressFrom);
//			message.setSubject("掌上律师大升级啦！！！");
//			Multipart mp = new MimeMultipart("related");
//			message.setSentDate(new Date());// 发送日期
//			BodyPart bodyPart = new MimeBodyPart();// 正文
//			bodyPart.setDataHandler(new DataHandler("在新设备上登录了帐号573245570@qq.com您的 Google " +
//					"帐号刚刚登录了一台新 Windows 设备。因此，我们向您发送这封电子邮件，以确保该操作是您本人所为。" +
//					"查看活动","text/html;charset=utf-8"));//网页格式
//			mp.addBodyPart(bodyPart);
//			message.setContent(mp);// 设置邮件内容对象
//			
//            //创建FileDAtaSource(用于添加附件)  
//           // FileDataSource fds = new FileDataSource("d:\\11.jpg");  
//            //BodyPart fileBodyPart = new MimeBodyPart();  
//            // 字符流形式装入文件  
//            //fileBodyPart.setDataHandler(new DataHandler(fds));  
//            // 设置附件文件名  
//            //fileBodyPart.setFileName("d:\\11.jpg");  
//            //mp.addBodyPart(fileBodyPart);  
//            //message.setContent(mp);  
//
//			Address addressTo;
//			
//				addressTo = new InternetAddress("573245570@qq.com");
//			
//				message.addRecipient(Message.RecipientType.TO , addressTo);
//				Transport.send(message);// 发送邮件
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		PropertyUtil.setProperty("sendtime", "12:30:00");
		MailUtil.sendMailByGmail("573245570@qq.com", PropertyUtil.getProperty("sendtitle"), "SRCE,CAFD,AAON,AAON,ACET,ATVI,ADUS,ADUS,ADVM");

//		TimeZone.setDefault(TimeZone.getTimeZone("PST"));  
//        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("PST"));  
//        int week = cal.get(Calendar.DAY_OF_WEEK);
//        
//        if(!(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY))
//        {
//         System.out.println("ok");
//        }
//        else  System.out.println("no");
	}
	 
}
