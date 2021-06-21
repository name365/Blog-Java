package github.smt.demo02;

/**
 * 总结：
 * - 真实对象和代理对象都要实现一个接口；
 * - 代理对象要代理真实角色。
 *
 * 好处：
 * - 代理对象可以做很多真实对象做不了的事情；
 * - 真实对象专注做自己的事。
 *
 * @author subeiLY
 * @create 2021-06-13 11:17
 */
public class TestStaticProxy {
    public static void main(String[] args) {
        WeddingCompany company = new WeddingCompany(new You());
        company.happyMary();
    }
}

// 结婚
interface Marry{
    void happyMary();
}

// 真实角色：你去结婚
class You implements Marry{
    @Override
    public void happyMary() {
        System.out.println("subei结婚了……");
    }
}

// 代理角色:婚庆公司帮你结婚
class WeddingCompany implements Marry{
    private Marry target;   // 代理--->真实目标角色，帮谁结婚

    public WeddingCompany(Marry target){
        this.target = target;
    }

    @Override
    public void happyMary() {
        after();
        this.target.happyMary();
        before();
    }

    private void after(){
        System.out.println("结婚前，布置现场！");
    }

    private void before(){
        System.out.println("结婚后，收尾款！");
    }
}
