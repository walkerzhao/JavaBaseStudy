package com.tencent.java.distributedlock;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

public class RedisLock {
	
//	public static JedisPool init() {
//		JedisPoolConfig redisConfig = new JedisPoolConfig();
//		redisConfig.setMaxWaitMillis(1000L * 10);
//		redisConfig.setTestOnBorrow(true);
//		JedisPoolConfig config = new JedisPoolConfig();
//		///usr/local/tools/mycon -p 6666 -f -N -g -L 19000:10.100.70.15:19000 10.100.71.217
//		// ssh -p 6666 -f -N -g -L 19001:10.100.70.15:19000 root@172.27.192.188
////		JedisPool pool = new JedisPool(config, "10.125.40.37", 19001, Protocol.DEFAULT_TIMEOUT, "mqq2015");
//		JedisPool pool = new JedisPool(config, "10.125.42.45", 19000, Protocol.DEFAULT_TIMEOUT, "mqq2015");
//		config.setMaxWaitMillis(1000);
//		config.setTestOnBorrow(true);
//		return pool;
//		// 如果不存在,则初始化0,如果存在,score达到阈值,则返回score,如果score未达到阈值,则清0
//		/*
//		 * System.out.println(jedis.eval("    "// + "local score = redis.call('zscore', KEYS[1], ARGV[1]);"// + "	   if(not score) then "// +++++++++++++++++++++++++++++//key不存在 +
//		 * "       redis.call('zadd', KEYS[1], 0, ARGV[1] )"// +++++// + "       return -1 "// +++++++++++++++++++++++++++++++++++// + "    end; " // ++++++++++++++++++++++++++++++++++++++++++// +
//		 * "score = tonumber(score)" + "local threshold = tonumber(ARGV[2]);"// + "    if (score >= threshold) then "// +++++++++//key对应的score是否达到阈值 + "      return score"//
//		 * ++++++++++++++++++++++++++++++++++// + "    else "// +++++++++++++++++++++++++++++++++++++++++++// + "      return 0; "// ++++++++++++++++++++++++++++++++++++// + "    end; ",//
//		 * ++++++++++++++++++++++++++++++++++++++++++// Arrays.asList("_test_luaxxxx"), Arrays.asList("_member", "2")));
//		 */
//
//	}
//	
//	public static void main(String[] args) {
//		
//		JedisPool pool = RedisLock.init();
//		Jedis jedis = pool.getResource();
//		
//		jedis.set("ewanzhaotest", "test", "NX", "EX", 100);
//		
//		
//	}

}
