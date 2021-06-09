package github.method;

/**
 * java中的按值调用
 * @author subeiLY
 * @create 2021-05-27 10:17
 */
public class HomeWork03 {

    /**
     * 交换两个对象
     * @param x
     * @param y
     */
    public static void swap(User x,User y){
        User temp = x;
        x = y;
        y = temp;
    }

    public static void main(String[] args) {
        User user = new User("user", 26);
        User stu = new User("stu", 18);
        System.out.println("调用前user的值："+ user.getName()+","+user.getAge());
        System.out.println("调用前stu的值："+ stu.getName()+","+stu.getAge());
        swap(user, stu);
        System.out.println("调用后user的值："+ user.getName()+","+user.getAge());
        System.out.println("调用后stu的值："+  stu.getName()+","+stu.getAge());
    }

}
