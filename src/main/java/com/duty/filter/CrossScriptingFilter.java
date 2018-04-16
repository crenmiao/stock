/**
 * 类名称:CrossScriptingFilter.java
 * 功能描述:防止XSS 注入
 * 作者:赵亚辉 
 * 时间:2015年1月19日-下午11:10:11
 * 版本信息:V1.0
 * CopyRight(C)：2015品尚电商-版权所有
 */
package com.duty.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CrossScriptingFilter implements Filter {


	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,	ServletException {
		CrossScriptingRequestWrapper xssRequest = new CrossScriptingRequestWrapper((HttpServletRequest) request);
		filterChain.doFilter(xssRequest, response);
	}

	public void destroy() {
		// TODO Auto-generated method stub		
	}


	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub		
	}

}