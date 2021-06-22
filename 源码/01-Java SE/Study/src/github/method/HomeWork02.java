package github.method;

/**
 * @author subeiLY
 * @create 2021-05-27 10:09
 */
public class HomeWork02 {

    public static void updateUser(User student){
        student.setName("subeiLY");
        student.setAge(22);
    }

    public static void main(String[] args) {
        User user = new User("SUBEI",20);
        System.out.println("调用user前的值:" + user.getName() + "," + user.getAge());
        updateUser(user);
        System.out.println("调用user后的值:" + user.getName() + "," + user.getAge());
    }
}
