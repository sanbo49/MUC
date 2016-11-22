package org.zsl.testmybatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
//@ContextConfiguration(locations = {"classpath:spring-mvc.xml"})
public class TestRedis {
@Test
public  void test() {
	 ApplicationContext ac =  new ClassPathXmlApplicationContext("classpath:spring-mvc.xml");
	 RedisClientTemplate redisClient = (RedisClientTemplate)ac.getBean("redisTemplate");
     redisClient.set("a", "abc");
     System.out.println(redisClient.get("a"));
//	    Jedis jedis = new Jedis("localhost");       // 连接到Redis服务器  
//	        jedis.set("greeting", "Hello, world!");     // 将字符串缓存到Redis服务器  
//	        System.out.println(jedis.get("greeting") + "========");  // 从Redis缓存中获取数据  
//	        jedis.set("name", "欢迎来到Redis缓存！");  
//	        String name= jedis.get("name");  
//	        System.out.print(name+"===========");  
}
}
