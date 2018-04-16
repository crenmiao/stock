package com.redis;

public interface Keys {

	/**
	 * key：登录错误次数,基于ip的
	 * */
	String KEY_ERROR_PASSWORD_TIMES_IP="err_pwd_times_ip";
	
	/**
	 * key：用户token
	 * */
	String KEY_USER_TOKEN  = "u_token";
	
	/**
	 * key：用户id
	 * */
	String KEY_USER_id  = "u_id";
}
