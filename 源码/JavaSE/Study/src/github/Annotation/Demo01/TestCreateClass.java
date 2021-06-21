package github.Annotation.Demo01;

/**
 * 测试class类的创建方式有哪些
 * @author subeiLY
 * @create 2021-06-18 10:40
 */
public class TestCreateClass {
    public static void main(String[] args) throws ClassNotFoundException {
        Person person = new Student();
        System.out.println("这个人是:" + person);

        // 方式一:通过对象查询
        Class c1 = person.getClass();
        System.out.println(c1.hashCode());

        // 方式二:forName获得
        Class c2 = Class.forName("github.Annotation.Demo01.Student");
        System.out.println(c2.hashCode());

        // 方式三:通过类名.class获得
        Class c3 = Student.class;
        System.out.println(c3.hashCode());

        // 方式四:基本类型的包装类都有一个Type
        Class c4 = Integer.TYPE;
        System.out.println(c4);

        // 获得父类类型
        Class c5 = c1.getSuperclass();
        System.out.println(c5);

    }
}

class Person{
    String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Student extends Person{
    public Student() {
        this.name = "学生";
    }
}

class Teacher extends Person{
    public Teacher(){
        this.name = "老师";
    }
}