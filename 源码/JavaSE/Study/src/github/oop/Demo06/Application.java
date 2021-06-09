package github.oop.Demo06;

/**
 * @author subeiLY
 * @create 2021-06-03 13:06
 */
// 一个项目应该只有一个main方法
public class Application {
    public static void main(String[] args) {
        // 类型间的转换：
        Person student = new Student();
        // 将student转化为Student类型
//        student.go();
        Student obj = (Student) student;
        obj.go();
    }
}
