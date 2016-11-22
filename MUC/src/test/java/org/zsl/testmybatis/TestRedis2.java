package org.zsl.testmybatis;

import static org.junit.Assert.*;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class TestRedis2 {

	@Test
	public void test() {
	    Jedis jedis = new Jedis("localhost");       // 连接到Redis服务器  
        jedis.set("greeting", "Hello, world!");     // 将字符串缓存到Redis服务器  
        System.out.println(jedis.get("greeting") + "========");  // 从Redis缓存中获取数据  
        jedis.set("name", "欢迎来到Redis缓存！");  
        String name= jedis.get("name");  
        System.out.print(name+"===========");  
//		fail("Not yet implemented");
	}

}
