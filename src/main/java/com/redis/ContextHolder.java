package com.redis;

import redis.clients.jedis.ShardedJedis;

/**
 *
 * @author ylz
 */
public class ContextHolder {
	
	private static final ThreadLocal<ShardedJedis> jdeisContextHolder = new ThreadLocal<ShardedJedis>();

	public static void setShardedJedis(ShardedJedis key) {
		jdeisContextHolder.set(key);
	}

	public static ShardedJedis getShardedJedis() {
		return jdeisContextHolder.get();
	}

	public static void clearShardedJedis() {
		jdeisContextHolder.remove();
	}
}
