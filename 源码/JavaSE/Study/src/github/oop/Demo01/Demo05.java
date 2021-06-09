package github.oop.Demo01;

/**
 * @author subeiLY
 * @create 2021-06-03 12:53
 */
public class Demo05 {
    // 引用传递：对象，本质还是值传递
    public static void main(String[] args) {
        Person person = new Person();

        System.out.println(person.name);

        Demo05.change(person);

        System.out.println(person.name);
    }

    public static void change(Person person) {
        person.name = "subeiLY";
    }
}
// 定义了一个Person类,有一个属性name
class Person{
    String name;
}