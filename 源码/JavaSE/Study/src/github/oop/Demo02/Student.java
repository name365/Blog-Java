package github.oop.Demo02;

/**
 * @author subeiLY
 * @create 2021-06-03 13:06
 */
// 学生类
public class Student {
    // 属性:字段
    String name; // null
    int age; // 0

    // 方法

    public void study() {
        System.out.println(this.name + "在学习！");
    }
}


/*
 public static void main(String[] args) {
        // 类：抽象的，实例化
        // 类的实例化会返回一个自己的对象！

        Student xiaoming = new Student();
        Student xiaohong = new Student();

        // 未赋值
        System.out.println(xiaohong.name);
        System.out.println(xiaohong.age);

        xiaoming.name = "小明";
        xiaoming.age = 3;

        // 已赋值
        System.out.println(xiaoming.name);
        System.out.println(xiaoming.age);
    }

 */