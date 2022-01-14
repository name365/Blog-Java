package com.github.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author subeiLY
 * @create 2022-01-14 17:05
 */
@Component
//@PropertySource(value = "classpath:application.properties")
public class User {
    // 从配置文件中获取值
//    @Value("${user2.name}") //从配置文件中取值
    private String name;
//    @Value("${user2.age}") // #{SPEL} Spring表达式
    private int age;
//    @Value("${user2.sex}") // 字面量
    private String sex;

    public User() {
    }

    public User(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
