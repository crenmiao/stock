/**
 * 类名称:CrossScriptingRequestWrapper.java
 * 功能描述:
 * 作者:赵亚辉 
 * 时间:2015年1月19日-下午11:10:58
 * 版本信息:V1.0
 * CopyRight(C)：2015品尚电商-版权所有
 */
package com.duty.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * <code>{@link CrossScriptingRequestWrapper}</code>
 * 
 * @author wqf
 */
public class CrossScriptingRequestWrapper extends HttpServletRequestWrapper {
    HttpServletRequest orgRequest = null;
    /**是否过滤*/
    boolean xssEncode = true;
    
    public CrossScriptingRequestWrapper(HttpServletRequest request) {
        super(request);
        String code = request.getParameter("xssFilter");
        xssEncode = (!CommonUtil.isEmpty(code) && code.equals("filter")) ? false : true;
        orgRequest = request;
    }

    /**
    * 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/>
    * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/>
    * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
    */
    @Override
    public String getParameter(String name) {
        String value = super.getParameter(CommonUtil.xssEncode(name));
        if (value != null&& xssEncode) {
            value = CommonUtil.xssEncode(value);
        }
        return value;
    }

    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = CommonUtil.xssEncode(values[i]);
        }
        return encodedValues;
    }

    /**
    * 覆盖getHeader方法，将参数名和参数值都做xss过滤。<br/>
    * 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/>
    * getHeaderNames 也可能需要覆盖
    */
    @Override
    public String getHeader(String name) {
        String value = super.getHeader(CommonUtil.xssEncode(name));
        if (value != null) {
            value = CommonUtil.xssEncode(value);
        }
        return value;
    }

    /**
    * 获取最原始的request
    *
    * @return
    */
    public HttpServletRequest getOrgRequest() {
        return orgRequest;
    }

    /**
    * 获取最原始的request的静态方法
    *
    * @return
    */
    public static HttpServletRequest getOrgRequest(HttpServletRequest req) {
        if (req instanceof CrossScriptingRequestWrapper) {
            return ((CrossScriptingRequestWrapper) req).getOrgRequest();
        }
        return req;
    }

}