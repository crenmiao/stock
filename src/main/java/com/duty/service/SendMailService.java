package com.duty.service;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duty.config.CommonConfigCache;
import com.duty.dao.SendRecordMapper;
import com.duty.dao.StockRecordMapper;
import com.duty.dao.UserMapper;
import com.duty.entity.SendRecord;
import com.duty.entity.StockRecord;
import com.duty.entity.User;
import com.duty.util.MailUtil;
import com.duty.util.PropertyUtil;

@Service("sendMailService")
public class SendMailService {
	@Autowired
	SendRecordMapper sendRecordMapper;
	@Autowired
	StockRecordMapper stockRecordMapper;
	@Autowired
	UserMapper userMapper;
	
	private static TimerTask task = null;
	private static Timer t = null;
	static long daySpan = 24 * 60 * 60 * 1000;
	
	@PostConstruct
	public void init() {

		// 一天的毫秒数
		

		// 规定的每天时间15:33:30运行
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd '"+ PropertyUtil.getProperty("sendtime") + "'");
		// 首次运行时间
		Date startTime;
		try {
			startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sdf
					.format(new Date()));

			// 如果今天的已经过了 首次运行时间就改为明天
			if (System.currentTimeMillis() > startTime.getTime())
				startTime = new Date(startTime.getTime() + daySpan);

			t = new Timer();

			task = new TimerTask() {
				@Override
				public void run() {
					// 要执行的代码
					try {
						System.out.println("发送邮件");
						
						String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
						SendRecord r = sendRecordMapper.getSendRecordByDate(today);
						if(r!=null){
							if(!r.getIssend().equals("1")){
								System.out.println(today+"判断是否生成");
								String stocksStr = stockRecordMapper.getStockStr(today);
								
								SendRecord sr = new SendRecord();
								sr.setId(UUID.randomUUID().toString().replace("-", ""));
								sr.setSenddate(today);
								
								if(stocksStr.equals("")){
									System.out.println(today+"生成股票为空");
									//sr.setIssend("0");
									//sendRecordMapper.insert(sr);
								}else{
									System.out.println(today+"已生成文件，按时发送邮件");
									List<User> users = userMapper.getUserBySubscipt("1");
									sendRecordMapper.updateStatus(today);
									for(User u:users){
				        				MailUtil.sendMailByGmail(u.getEmail(), PropertyUtil.getProperty("sendtitle"), stocksStr);
				        				
				        			}							}
							}
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
			};

			// 以每24小时执行一次
			t.scheduleAtFixedRate(task, startTime, daySpan);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public void resetTask(Date startTime){
		if(task!=null){
			task.cancel();
		}
		int i= t.purge();
		t.cancel();
		
		t = new Timer();

		task = new TimerTask() {
			@Override
			public void run() {
				// 要执行的代码
				try {
					System.out.println("发送邮件");
					
					String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
					SendRecord r = sendRecordMapper.getSendRecordByDate(today);
					if(r!=null){
						if(!r.getIssend().equals("1")){
							System.out.println(today+"判断是否生成");
							String stocksStr = stockRecordMapper.getStockStr(today);
							
							SendRecord sr = new SendRecord();
							sr.setId(UUID.randomUUID().toString().replace("-", ""));
							sr.setSenddate(today);
							
							if(stocksStr.equals("")){
								System.out.println(today+"生成股票为空");
								//sr.setIssend("0");
								//sendRecordMapper.insert(sr);
							}else{
								System.out.println(today+"已生成文件，按时发送邮件");
								List<User> users = userMapper.getUserBySubscipt("1");
								sendRecordMapper.updateStatus(today);
								for(User u:users){
			        				MailUtil.sendMailByGmail(u.getEmail(), PropertyUtil.getProperty("sendtitle"), stocksStr);
			        				
			        			}							}
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		};
		
		t.scheduleAtFixedRate(task, startTime, daySpan);
	}
}
