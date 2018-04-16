package com.duty.controller;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duty.dao.UserMapper;
import com.duty.entity.User;
import com.duty.service.ReadCsvService;
import com.duty.service.SendMailService;
import com.duty.util.MD5;
import com.duty.util.ResponseData;
import com.duty.util.StringUtil;
import com.duty.util.UserConst;


@Controller
public class IndexController {

	@Autowired
	UserMapper userMapper;
	@Autowired
	ReadCsvService readCsvService;
	
	
	@RequestMapping("/login")
	
	public String login(HttpServletRequest  req){
		return "login";
	}
	
	@RequestMapping("/index")
	public String index(HttpServletRequest req){
		//readCsvService.readCsv();
		Object o = req.getSession().getAttribute(UserConst.USER_SFJ_KEY);
		if(o==null){
			return "redirect:login";
		} 
		
		return "index";
	}
	
	@RequestMapping("/loginSubmit")
	@ResponseBody
	public ResponseData loginSubmit(HttpServletRequest req,String email,String pwd){
		if(StringUtil.isEmpty(email)){
			return new ResponseData("email_null","");
		}
		if(StringUtil.isEmpty(pwd)){
			return new ResponseData("pwd_null","");
		}
		pwd = MD5.encodeByMd5AndSalt(pwd, "123");
		
		User u = userMapper.getUser(email);
		if(u==null){
			return new ResponseData("noregister","");
		}
		
		User user = userMapper.login(email, pwd);
		if(user!=null){
			if(user.getType().equals("2")){
				req.getSession().setAttribute(UserConst.USER_ADMIN_KEY, user);
			}else{
				req.getSession().setAttribute(UserConst.USER_SFJ_KEY, user);
			}
			//req.setAttribute("type", user.getType());
			return new ResponseData("success",user.getType());
		}else{
			return new ResponseData("error","");
		}
		
	}
	

	@RequestMapping("/logout")
	public String logout(HttpServletRequest req,String email,String pwd){
		req.getSession().removeAttribute(UserConst.USER_SFJ_KEY);
		req.getSession().removeAttribute(UserConst.USER_ADMIN_KEY);
		return "redirect:login";
		//return new ResponseData("success","");
		
	}
	
	
	@RequestMapping("/register")
	@ResponseBody
	public ResponseData register(HttpServletRequest req,String email,String pwd){
		if(StringUtil.isEmpty(email)){
			return new ResponseData("email_null","");
		}
		if(StringUtil.isEmpty(pwd)){
			return new ResponseData("pwd_null","");
		}
		
		User u = userMapper.getUser(email);
		if(u!=null){
			return new ResponseData("registered","");
		}
		pwd = MD5.encodeByMd5AndSalt(pwd, "123");
		User user = new User();
		user.setEmail(email);
		user.setPwd(pwd);
		user.setRegisttime(new Date());
		user.setSubscription("0");
		user.setType("1");
		user.setId(UUID.randomUUID().toString().replace("-", ""));
		boolean b = userMapper.insertUser(user);
		if(b){
			req.getSession().setAttribute(UserConst.USER_SFJ_KEY, user);
			return new ResponseData("success","");
		}else{
			return new ResponseData("error","");
		}
		
	}
	
	
	
	@RequestMapping("/subscript")
	@ResponseBody
	public ResponseData subscript(HttpServletRequest req){
		Object o = req.getSession().getAttribute(UserConst.USER_SFJ_KEY);
		if(o==null){
			return new ResponseData("nologin","");
		} 
		User u = (User)o;
		
		if(u.getSubscription().equals("1")){
			return new ResponseData("subscripted","");
		}
			
		boolean b = userMapper.subscript(u.getId());
		if(b){
			u.setSubscription("1");
			req.getSession().setAttribute(UserConst.USER_SFJ_KEY, u);
			return new ResponseData("success","");
		}else{
			return new ResponseData("error","");
		}
	}
	
	
	@RequestMapping("/unsubscript")
	@ResponseBody
	public ResponseData unsubscript(HttpServletRequest req){
		
		Object o = req.getSession().getAttribute(UserConst.USER_SFJ_KEY);
		if(o==null){
			return new ResponseData("nologin","");
		}
		User u = (User)o;

		if(u.getSubscription().equals("0")){
			return new ResponseData("unsubscripted","");
		}
		
		boolean b = userMapper.unsubscript(u.getId());
		if(b){
			u.setSubscription("0");
			req.getSession().setAttribute(UserConst.USER_SFJ_KEY, u);
			return new ResponseData("success","");
		}else{
			return new ResponseData("error","");
		}
	}
	
	@RequestMapping("/delUser")
	@ResponseBody
	public ResponseData delUser(HttpServletRequest req,String id){
		
		
		boolean b = userMapper.delUser(id);
		if(b){
			
			return new ResponseData("success","");
		}else{
			return new ResponseData("error","");
		}
	}
	
}
