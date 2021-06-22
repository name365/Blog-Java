package github.oop.Demo03;

/**
 * @author subeiLY
 * @create 2021-06-03 21:53
 */
// 类
public class Student {
    // 属性私有：添加private关键字
    private String name; // 名字
    private int id; // 学号
    private char sex; // 性别

    // get 获得这个数据
    public String getName() {
        return name;
    }

    // set给这个数据设置值
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }
}


/*
    public static void main(String[] args) {
        Student s1 = new Student();
        String name = s1.getName();
        s1.setName("subeiLY");

        System.out.println(s1.getName());
    }
 */