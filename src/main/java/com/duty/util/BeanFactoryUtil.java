package com.duty.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanFactoryUtil {
    private static ApplicationContext ctx_producer = null;
 
	/**
	 * 本地applicationContext.xml的相对位置
	 */
	public final static String ApplicationContextPath = "/applicationContext.xml";
	
    public static void init() {
        if (ctx_producer == null) {
        	synchronized (BeanFactoryUtil.class) {
        		if(ctx_producer == null){
        			String[] configLocations = new String[]{
                            ApplicationContextPath};
                    ctx_producer = new ClassPathXmlApplicationContext(configLocations);
        		} 
        		
			}
        }
    }

    public static ApplicationContext getContext() {
        init();
        return ctx_producer;
    }

	public static ApplicationContext getCtx_producer() {
		return ctx_producer;
	}

	public static void setCtx_producer(ApplicationContext ctxProducer) {
		ctx_producer = ctxProducer;
	}
    
}
