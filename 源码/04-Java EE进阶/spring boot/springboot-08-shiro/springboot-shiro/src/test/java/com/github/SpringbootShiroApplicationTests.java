package com.github;

import com.github.pojo.User;
import com.github.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootShiroApplicationTests {

    @Autowired
    UserServiceImpl userService;

    @Test
    void contextLoads() {
        System.out.println(userService.queryUserByName("root"));
    }

}
