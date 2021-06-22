package github.oop.Demo04;

/**
 * @author subeiLY
 * @create 2021-06-04 12:24
 */
public class Student extends Person{
    private String name = "subeiLY";

    public Student() {
        System.out.println("Student执行了无参！！！");
    }

    public void print(){
        System.out.println("student");
    }

    public void test(String name){
        System.out.println(name);
        System.out.println(this.name);
        System.out.println(super.name);
    }

    public void test2(){
        print();
        this.print();
        super.print();
    }
}
