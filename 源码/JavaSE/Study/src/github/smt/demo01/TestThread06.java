package github.smt.demo01;

import java.util.concurrent.*;

/**
 * @author subeiLY
 * @create 2021-06-13 9:34
 */
public class TestThread06 implements Callable<Boolean> {
    private String url;     // 网络图片地址
    private String name;    // 报错的文件名

    // 有参构造
    public TestThread06(String url,String name){
        this.url = url;
        this.name = name;
    }

    public static void main(String[] args) throws ExecutionException,InterruptedException {
        TestThread06 p1 = new TestThread06("http://browser9.qhimg.com/bdm/768_474_0/t010824ab8b5cdfa138.jpg", "ptr.png");
        TestThread06 p2 = new TestThread06("http://browser9.qhimg.com/bdm/768_474_0/t012468d019e3ce466b.jpg", "ptr02.png");
        TestThread06 p3 = new TestThread06("http://browser9.qhimg.com/bdm/384_237_0/t01039b44f7c7ca5ca3.jpg", "ptr03.png");

        // 执行服务
        ExecutorService service = Executors.newFixedThreadPool(3);

        // 提交执行
        Future<Boolean> submit = service.submit(p1);
        Future<Boolean> submit1 = service.submit(p2);
        Future<Boolean> submit2 = service.submit(p3);

        // 获取结果
        boolean aBoolean = submit.get();
        boolean aBoolean1 = submit1.get();
        boolean aBoolean2 = submit2.get();

        // 关闭服务
        service.shutdownNow();
    }

    // 下载图片线程的执行体
    @Override
    public Boolean call() throws Exception {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url,name);
        System.out.println("下载的文件：" + name);
        return true;
    }
}
