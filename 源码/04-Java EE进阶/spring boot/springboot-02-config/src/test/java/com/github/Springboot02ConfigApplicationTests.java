package com.github;

import com.github.pojo.Dog;
import com.github.pojo.Person;
import com.github.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot02ConfigApplicationTests {

    @Autowired
//    Dog dog;    // 注入宠物类
//    Person person;  // 注入人类类
//    User user;

    @Test
    void contextLoads(){
        // 打印宠物对象
//        System.out.println(dog);
        // 打印人类类
//        System.out.println(person);
//        System.out.println(user);
    }

}
