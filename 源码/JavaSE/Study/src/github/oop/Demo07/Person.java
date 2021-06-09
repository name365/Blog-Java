package github.oop.Demo07;

/**
 * @author subeiLY
 * @create 2021-06-05 4:43
 */
public class Person {

    {
        System.out.println("匿名代码块");
    }

    static {
        System.out.println("静态代码块");
    }

    public Person(){
        System.out.println("构造方法");
    }

    public static void main(String[] args) {
        Person person = new Person();
        System.out.println("********************");
        Person person2 = new Person();

    }
}
