package com.duty.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JSON 数据请求返回内容
 *
 */
public class ResponseData {
	private String ec;
	private String em = "";
	private String requestURI = "";
	private String execptionTrace = "";
	private Long currentTime = System.currentTimeMillis();

	private Object obj;
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public ResponseData() {

	}

	public ResponseData(String ec) {
		this(ec, null, null);
	}

	public ResponseData(String ec, String message) {
		this(ec, message, null);
	}

	public ResponseData(String success, String failMessage, Object message) {
		this.ec = success;
		if (failMessage == null)
			failMessage = "";
		this.em = failMessage;
		if (message == null)
			message = "";
		this.obj = message;
		logger.info("{ec:" + ec + "," + "em:" + em + "}");
	}

	public String getEc() {
		return ec;
	}

	public void setEc(String ec) {
		this.ec = ec;
	}

	public String getEm() {
		return em;
	}

	public void setEm(String em) {
		this.em = em;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

	public String getExecptionTrace() {
		return execptionTrace;
	}

	public void setExecptionTrace(String execptionTrace) {
		this.execptionTrace = execptionTrace;
	}

	public void setCurrentTime(Long currentTime) {
		this.currentTime = currentTime;
	}

	public Long getCurrentTime() {
		return currentTime;
	}
}
