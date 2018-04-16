package com.duty.task;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.duty.util.MailUtil;


@Component 
//@PropertySource("classpath:profile/config.properties")
public class ManageSql { 
   
	
    @Scheduled(cron="0 52 23 ? * *") //间隔5秒执行   
    
    public void test(){ 
    	try {
			MailUtil.sendMail("573245570@qq.com", "ceshi12", "ddd");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    } 
} 