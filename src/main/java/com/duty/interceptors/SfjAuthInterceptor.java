package com.duty.interceptors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.duty.util.StringUtil;
import com.duty.util.UserConst;




/**
 * 权限拦截器
 * 
 * @author 
 * 
 */
public class SfjAuthInterceptor implements HandlerInterceptor {
	 
	private static final Logger logger = Logger.getLogger(SfjAuthInterceptor.class);
	//private SystemService systemService;
	private List<String> excludeUrls;

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	 

	/**
	 * 在controller后拦截
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 在controller前拦截
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		String requestPath = StringUtil.getRequestPath(request);// 用户访问的资源地址
		logger.info("-----authInterceptor----requestPath------"+requestPath);


		if (excludeUrls.contains(requestPath)) {
			return true;
		} else {
			HttpSession session = request.getSession(); 
	        if(session==null||session.getAttribute(UserConst.USER_SFJ_KEY)==null)
	        {
	        	response.sendRedirect(request.getSession().getServletContext().getContextPath()+"/login");
				return false;
	        	
	        } else {
	        	return true;
			}

		}
	}

	 
	
	/**
	 * 转发
	 * 
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "forword")
	public ModelAndView forword(HttpServletRequest request) {
		return new ModelAndView(new RedirectView("loginController.do?login"));
	}

	private void forward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.sendRedirect(request.getSession().getServletContext().getContextPath()+"/frontLogin.act");

	}

}
