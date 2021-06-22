package github.oop.Demo02;

/**
 * @author subeiLY
 * @create 2021-06-03 14:23
 */
public class Person {
    String name;

    // 定义无参构造器
    /*
    1.使用new关键字，必须要用构造器
    2.用来初始化值
     */
    public Person(){
        this.name = "subeiLY";
    }

    // 有参构造器
    // 一旦定义了有参构造，无参就必须显示定义
    public Person(String name){
        this.name = name;
    }
}


/*

public static void main(String[] args) {
        // 实例化一个对象
        // 调用无参
        Person person = new Person();

        System.out.println(person.name);

        // 调用有参
        Person person2 = new Person("subei365");

        System.out.println(person2.name);
    }

 */