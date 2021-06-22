package github.oop.Demo08;

/**
 * @author subeiLY
 * @create 2021-06-05 4:56
 */

// abstract 抽象类：类
public abstract class Action {

    // 约束
    // abstract ，抽象方法，只有方法名字，没有方法实现！
    public abstract void doSomething();

    /*
    抽象类特点:
        1.不能new这个抽象类，它只能依靠子类去实现它；约束！！！
        2.抽象类中可以写普通方法；
        3.抽象方法必须在抽象类中。
     */

}
