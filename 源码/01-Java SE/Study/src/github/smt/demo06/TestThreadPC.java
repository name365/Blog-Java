package github.smt.demo06;

/**
 * 测试:生产者消费者模型-->利用缓冲区解决:管程法
 * @author subeiLY
 * @create 2021-06-17 15:22
 */
public class TestThreadPC {
    public static void main(String[] args) {
        SynContainer synContainer = new SynContainer();
        new Producer(synContainer).start();
        new Consumer(synContainer).start();
    }
}

// 生产者
class Producer extends Thread{
    // 缓冲区
    SynContainer container;

    public Producer(SynContainer container) {
        this.container = container;
    }

    // 生产
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            container.push(new Product(i));
            System.out.println("生产了:" + i + "件产品。");
        }
    }
}

// 消费者
class Consumer extends Thread{
    // 缓冲区
    SynContainer container;

    public Consumer(SynContainer container) {
        this.container = container;
    }

    // 消费
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("消费了:" + container.pop().id + "件产品。");
        }
    }
}

// 产品
class Product{
    int id; // 产品编号
    public Product(int id){
        this.id = id;
    }
}

// 缓冲区
class SynContainer {
    // 需要一个容器大小
    Product[] products = new Product[10];
    // 容器计数器
    int count = 0;

    // 生产者放入产品
    public synchronized void push(Product product) {
        // 如果容器满了,需要等待消费者消费
        /*
        如果是if的话，假如消费者1消费了最后一个，
        这是index变成0此时释放锁被消费者2拿到而不是生产者拿到，
        这时消费者的wait是在if里所以它就直接去消费index-1下标越界，
        如果是while就会再去判断一下index得值是不是变成0了
        */
        while (count == products.length) {
            // 通知消费者消费,等待生产
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 如果没有满,需要丢入产品
        products[count] = product;
        count++;
        // 通知消费者消费
        this.notifyAll();
    }

    // 消费者消费产品
    public synchronized Product pop() {
        // 判断是否能消费
        while (count <= 0) {
            // 等待生产者生产
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 如果可以消费
        count--;
        Product product = products[count];
        // 吃完了 通知生产者生产
        this.notifyAll();
        return product;
    }
}