package com.redis;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import redis.clients.jedis.ShardedJedisPool;

/**
 *
 * @author ylz
 */
@Aspect
@Component
public class RedisAspect {

	protected Logger log = Logger.getLogger(getClass().getName());
	
	//@Autowired
	//@Qualifier("shardedJedisPool")
	private ShardedJedisPool pool;
	
	//@Pointcut("execution(* com.duty.service.*.*(..))||execution(* com.redis.*.*(..))")
	private void cutMethod(){
		System.out.println("执行方法");
	} 
	
	//@Before("cutMethod()")
	public void configJedis(JoinPoint jp) throws Throwable {
		System.out.println("执行方法before");
		ContextHolder.setShardedJedis(pool.getResource());
	}
	
	//@After("cutMethod()")
	public void releaseJedis(){
		pool.returnResource(ContextHolder.getShardedJedis());
		ContextHolder.clearShardedJedis();
	}
}
