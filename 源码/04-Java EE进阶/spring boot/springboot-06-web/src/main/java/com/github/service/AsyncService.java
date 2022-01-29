package com.github.service;

import org.springframework.stereotype.Service;

/**
 * @author subeiLY
 * @create 2022-01-29 11:36
 */
@Service
public class AsyncService {
    public void hello(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("业务进行中....");
    }
}
