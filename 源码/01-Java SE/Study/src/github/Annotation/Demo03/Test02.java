package github.Annotation.Demo03;

/**
 * 什么叫反射
 * @author subeiLY
 * @create 2021-06-18 10:13
 */
public class Test02 {
    public static void main(String[] args) throws ClassNotFoundException {
        // 通过反射获取class对象
        Class name = Class.forName("github.Annotation.Demo01.User");
        System.out.println(name);

        Class c1 = Class.forName("github.Annotation.Demo01.User");
        Class c2 = Class.forName("github.Annotation.Demo01.User");
        Class c3 = Class.forName("github.Annotation.Demo01.User");
        Class c4 = Class.forName("github.Annotation.Demo01.User");

        /*
        一个类在内存中只有一个Class对象
        一个类被加载后,类的整个结构都会被封装在Class对象中
        public native int hashCode();返回该对象的hash码值
        注：哈希值是根据哈希算法算出来的一个值，这个值跟地址值有关，但不是实际地址值。
         */

        System.out.println(c1.hashCode());
        System.out.println(c2.hashCode());
        System.out.println(c3.hashCode());
        System.out.println(c4.hashCode());
    }
}

// 实体类:pojo entity
class User{
    private int id;
    private int age;
    private String name;

    public User() {
    }

    public User( String name, int id, int age) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}