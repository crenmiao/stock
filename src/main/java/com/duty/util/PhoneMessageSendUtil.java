package com.duty.util;

import java.util.HashMap;


public class PhoneMessageSendUtil
{

	/**
	 * 手机短信
	 * 
	 * @param receivePhone
	 * @param message
	 * @param replyCount
	 * @return
	 * @throws Exception
	 */
	public static  boolean sendMessageByHttpWebService(HashMap<String, Object> param) throws Exception
	{
		
		String res = HttpURLConnectionUtil.sendPostRequest(param.get("url").toString(), param);
		System.out.println("send phone message ------ "+res);
		
		return true;
	}
}
