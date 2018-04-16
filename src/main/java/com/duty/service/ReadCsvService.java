package com.duty.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.InitBinder;


import com.duty.config.CommonConfigCache;
import com.duty.dao.SendRecordMapper;
import com.duty.dao.StockRecordMapper;
import com.duty.dao.UserMapper;
import com.duty.entity.SendRecord;
import com.duty.entity.StockRecord;
import com.duty.entity.User;
import com.duty.util.MailUtil;
import com.duty.util.PathUtil;
import com.duty.util.PropertyUtil;

import au.com.bytecode.opencsv.CSVReader;

@Service("readCsvService")
public class ReadCsvService {
	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	SendRecordMapper sendRecordMapper;
	@Autowired
	StockRecordMapper stockRecordMapper;
	@Autowired
	UserMapper userMapper;
	
	@PostConstruct
	public void init()
	{
		Timer timer2 = new Timer();
		timer2.schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				try
				{
					readCsv();

				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}, 0, 1*60*1000);
	}

	
	
	
	/**
	 * 读取csv文件
	 */
	public void readCsv(){
		String date = f.format(new Date());
	
		System.out.println("----------------------当前时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"---------------------------");
		List<StockRecord> stocks = stockRecordMapper.getStockRecordsByDate(date);
		if(stocks.size()==0){
			
			String filePath=CommonConfigCache.getFilePath();
			filePath = PathUtil.getCSVUrl(filePath, date);
			File file = new File(filePath);  
			
			if (file.exists()){
				System.out.println("----------文件存在-----------------");
			
		        FileReader fReader = null;
		        CSVReader csvReader = null;
		        List<StockRecord> datas = new ArrayList<StockRecord>();
				try {
					fReader = new FileReader(file);
					
			        csvReader = new CSVReader(fReader);  
			        List<String[]> list = csvReader.readAll(); 
			        String lastStr = "";
			        for(String s : list.get(list.size()-1))  {
			        	lastStr+= s;
			        }
			        if(lastStr.equals(CommonConfigCache.getEndMark())){
			        	for(String[] ss : list){  
			        		String str = "";
			        		StockRecord stock = new StockRecord();
				            for(String s : ss){ 
				                if(null != s && !s.equals(""))  
				                	str+=s;
				            }
				            if(str.equals(CommonConfigCache.getEndMark()))break;
				            stock.setId(UUID.randomUUID().toString().replace("-", ""));
				            stock.setCreatetime(date);
				            stock.setName(str);
				            datas.add(stock);
				            //System.out.println(str);
				        }
			        	boolean b = stockRecordMapper.insertStockRecords(datas);
			        	if(b){
			        		SendRecord r = sendRecordMapper.getSendRecordByDate(date);
			        		
		        			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		        			Date bt=sdf.parse(date+" "+PropertyUtil.getProperty("sendtime")); 
		        			Date et=new Date();
		        			if (bt.before(et)){ //过了发送时间
		        				List<User> users = userMapper.getUserBySubscipt("1");
		        				String stocksStr = stockRecordMapper.getStockStr(date);
		        				if(r!=null){
				        			if(r.getIssend().equals("0")){//未发送
					        			System.out.println("生成文件完毕，发送邮件（未按时）");
					        			sendRecordMapper.updateStatus(date);
					        			
					        			for(User u:users){
					        				MailUtil.sendMailByGmail(u.getEmail(), PropertyUtil.getProperty("sendtitle"), stocksStr);
					        				
					        			}
				        			}
				        		}else{
				        			SendRecord sr = new SendRecord();
									sr.setId(UUID.randomUUID().toString().replace("-", ""));
									sr.setSenddate(date);
									System.out.println(date+"生成文件完毕，发送（未按时1）");
									sr.setIssend("1");
									sendRecordMapper.insert(sr);
									for(User u:users){
				        				MailUtil.sendMailByGmail(u.getEmail(), PropertyUtil.getProperty("sendtitle"), stocksStr);
				        				
				        			}			        	 
				        		}
		        			}else{ 
		        				SendRecord sr = new SendRecord();
								sr.setId(UUID.randomUUID().toString().replace("-", ""));
								sr.setSenddate(date);
								System.out.println(date+"生成文件完毕，等待发送");
								sr.setIssend("0");
								sendRecordMapper.insert(sr);
		        			} 
			        		 
			        	}
			        }
				} catch (Exception e) {
					
					e.printStackTrace();
				}finally{
					try {
						if (fReader != null) {  
							fReader.close();
						}
						if(csvReader!=null){
							csvReader.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					} 
				}
			}
		}
	}
}
