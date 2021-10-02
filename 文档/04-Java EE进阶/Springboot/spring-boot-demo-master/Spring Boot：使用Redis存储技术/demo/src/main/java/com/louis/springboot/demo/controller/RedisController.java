package com.louis.springboot.demo.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.louis.springboot.demo.model.User;

@RestController
public class RedisController {

	@Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

	@GetMapping("/testString")
	public String testString()  {
		stringRedisTemplate.opsForValue().set("name", "louis");
		String name = stringRedisTemplate.opsForValue().get("name");
		return "the value of key 'name' is : " + name ;
	}
	
	@GetMapping("/testObject")
	public String testObject()  {
		StringBuilder result = new StringBuilder();
		User user = new User("louis", "123");
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("sys.user", user);
        operations.set("sys.user.timeout", user, 1, TimeUnit.SECONDS);	// 设置1秒后过期
        result.append("过期前：").append("\n");
        result.append("sys.user=" + operations.get("sys.user")).append("\n");
        result.append("sys.user.timeout=" + operations.get("sys.user.timeout"));
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        result.append("\n").append("过期后：").append("\n");
        result.append("sys.user=" + operations.get("sys.user")).append("\n");
        result.append("sys.user.timeout=" + operations.get("sys.user.timeout"));
        return result.toString();
	}
	
	@GetMapping("/getUser")
    @Cacheable(value="user-key")
    public User getUser() {
		User user = new User("louis", "123");
        System.out.println("用户对象缓存不存在，返回一个新的用户对象。");
        return user;
    }
}