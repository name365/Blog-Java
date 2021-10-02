package com.louis.springboot.demo.mem;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.spy.memcached.MemcachedClient;

@Configuration
public class MemcacheConfig {

	@Value("${memcache.id}")
    private String ip;

	@Value("${memcache.id}")
    private int port;


    @Bean
    public MemcachedClient getClient() {
        MemcachedClient memcachedClient = null;
		try {
        	memcachedClient  = new MemcachedClient(new InetSocketAddress(ip, port));
		} catch (IOException e) {
			e.printStackTrace();
		}
        return memcachedClient;
    }
}