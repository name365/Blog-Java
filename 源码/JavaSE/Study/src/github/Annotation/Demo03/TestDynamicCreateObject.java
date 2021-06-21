package github.Annotation.Demo03;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 动态的创建对象,通过反射
 * @author subeiLY
 * @create 2021-06-18 23:36
 */
public class TestDynamicCreateObject {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        // 获得Class对象
        Class c1 = Class.forName("github.Annotation.Demo03.User");

        // 构造一个对象
        User user = (User) c1.newInstance();    // 本质上调用了类的无参构造器
        System.out.println(user);

        // 通过构造器创建对象
        Constructor constructor = c1.getDeclaredConstructor(String.class, int.class, int.class);
        User user1 = (User) constructor.newInstance("光荣时代",001,17);
        System.out.println(user1);

        // 通过反射调用普通方法
        User user2 = (User) c1.newInstance();
        // 通过反射获取一个方法
        Method setName = c1.getDeclaredMethod("setName", String.class);
        // invoke:激活
        // (对象,"方法值")
        setName.invoke(user2, "some");
        System.out.println(user2.getName());

        // 通过反射操作属性
        User user3 = (User) c1.newInstance();
        Field name = c1.getDeclaredField("name");

        // 不能直接操作私有属性,我们需要关闭程序的安全检测,属性或方法的setAccessible(true)
        // 设置安全检测
        name.setAccessible(true);

        name.set(user3, "some2");
        System.out.println(user3.getName());
    }
}
