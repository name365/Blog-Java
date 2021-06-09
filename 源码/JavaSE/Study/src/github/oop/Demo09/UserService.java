package github.oop.Demo09;

/**
 * @author subeiLY
 * @create 2021-06-05 5:11
 */
// interface 定义的关键字，接口都需要有实现类
public interface UserService {
    // 接口中的所有东西都是抽象的public abstract
    void run(String name);

    void add(String name);
    void delete(String name);
    void update(String name);
    void query(String name);
}

