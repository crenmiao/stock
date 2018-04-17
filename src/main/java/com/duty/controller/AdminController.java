package com.duty.controller;
 
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duty.dao.ConfigMapper;
import com.duty.dao.UserMapper;
import com.duty.entity.Config;
import com.duty.entity.QueryPage;
import com.duty.entity.User;
import com.duty.service.SendMailService;
import com.duty.util.MD5;
import com.duty.util.MailUtil;
import com.duty.util.PageTag;
import com.duty.util.PropertyUtil;
import com.duty.util.ResponseData;
import com.duty.util.UserConst;

@Controller
public class AdminController {

	@Autowired
	UserMapper userMapper;
	@Autowired
	ConfigMapper configMapper;
	@Autowired
	SendMailService sendMailService;
	
	
	
	/**
	 * 跳转登陆页
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin")
	public String login(HttpServletRequest request){
		return "redirect:/login";
	}

	
	@RequestMapping("/test")
	@ResponseBody
	public String test(HttpServletRequest request){
		try {
			MailUtil.sendMail("573245570@qq.com", "注册通知", "恭喜您注册成功！");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 登陆提交
	 * @param request
	 * @param cooperation
	 * @return
	 */
	@RequestMapping("/admin/loginSubmit")
	@ResponseBody
	public ResponseData loginSubmit(HttpServletRequest request,String phone,String password){
		password = MD5.encodeByMd5AndSalt(password, "123");
		
		User user = userMapper.login(phone, password);
		if(user!=null){
			if(!user.getEmail().equals("admin")){
				return new ResponseData("error","");
			}
			request.getSession().setAttribute(UserConst.USER_ADMIN_KEY, user);
			return new ResponseData("success","");
		}else{
			return new ResponseData("error","");
		}
	}
	
	
	/**
	 * 跳转首页
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin/main")
	public String main(HttpServletRequest request,PageTag page){
		User u = getUser(request);
		if(u==null){
			return "redirect:/login";
		}
		
		//int pageNum=page.getPageNum();
		
		if(page.getPageNum() < 1)
			page.setPageNum(1);
		if(page.getPageSize()==0)
			page.setPageSize(10);
		
		User bean = new User();
		bean.setType("1");
		bean.setPageNo(page.getPageNum() );
		bean.setPageSize(page.getPageSize());
		bean.setOffset((page.getPageNum() -1)*page.getPageSize());
		
		List<User> users = userMapper.getUsers(bean);
		int count = userMapper.countUser(bean);
		page.setTotleNum(count);
		request.setAttribute("users", users);
		//request.setAttribute("pageNum", pageNum);
		//request.setAttribute("pageSize", 10);
		//request.setAttribute("totalSize", count);
		request.setAttribute("bean", bean);
		request.setAttribute("page", page);
		return "admin/userList";
	}
	
	
	/**
	 * 用户列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin/config")
	public String problemList(Model model,HttpServletRequest request){
		User u = getUser(request);
		if(u==null){
			return "redirect:/login";
		}
		return "admin/config";
	}
	
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin/configSave")
	@ResponseBody
	public ResponseData configSave(HttpServletRequest request,String sendtime){
		User u = getUser(request);
		if(u==null){
			return new ResponseData("nologin","");
		}
		PropertyUtil.setProperty("sendtime",sendtime );
		
		// 一天的毫秒数
		long daySpan = 24 * 60 * 60 * 1000;
		
		
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd '"+ PropertyUtil.getProperty("sendtime") + "'");
		// 首次运行时间
		Date startTime;
		try {
			startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sdf
					.format(new Date()));

			// 如果今天的已经过了 首次运行时间就改为明天
			if (System.currentTimeMillis() > startTime.getTime())
				startTime = new Date(startTime.getTime() + daySpan);
			sendMailService.resetTask(startTime);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
//		List<Config> configs = configMapper.getConfig();
//		Config c = new Config();
//		c.setSenttime(senttime);
//		if(configs.size()>0){
//			
//		}else{
//			configMapper.insertConfig(u);
//		}
			return new ResponseData("success","");
//		}else{
//			return new ResponseData("error","");
//		}
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin/updateTem")
	@ResponseBody
	public ResponseData updateTem(HttpServletRequest request,String title,String content){
		User u = getUser(request);
		if(u==null){
			return new ResponseData("nologin","");
		}
		PropertyUtil.setProperty("sendtitle",title );
		PropertyUtil.setProperty("sentContent",content );
		  
			return new ResponseData("success","");
 
	}
	
	
	public User getUser(HttpServletRequest request){
		Object o = request.getSession().getAttribute(UserConst.USER_ADMIN_KEY);
		if(o==null){
			return null;
		} else{
			return (User)o;
		}
	}
	
	@RequestMapping("md5Methon")
	@ResponseBody
	public String md5Methon(HttpServletRequest req,String pwd){
		return 	MD5.encodeByMd5AndSalt(pwd, "123");

	}
}
