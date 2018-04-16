package com.duty.entity;

import java.util.Date;

public class User extends QueryPage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7591508731171365694L;
	private String id;
	private String name;
	private String email;
	private String pwd;
	private String type;
	private String subscription;//是否关注
	private Date registtime;//注册时间
	private Date subtime;//关注时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSubscription() {
		return subscription;
	}
	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}
	public void setSubtime(Date subtime) {
		this.subtime = subtime;
	}
	public Date getSubtime() {
		return subtime;
	}
	public void setRegisttime(Date registtime) {
		this.registtime = registtime;
	}
	public Date getRegisttime() {
		return registtime;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	
	
}
