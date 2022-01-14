package com.github.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author subeiLY
 * @create 2022-01-13 11:52
 */

/**
 *  注册bean到容器中
 */
@Component
public class Dog {
    @Value("阿伟")
    private String name;
    @Value("16")
    private Integer age;

    public Dog(){
    }

    public Dog(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
