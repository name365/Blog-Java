package github.smt.demo01;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author subeiLY
 * @create 2021-06-12 13:41
 */
// 练习Thread，实现多线程同步下载图片
public class TestThread02 implements Runnable{
    private String url;
    private String name;

    // 有参构造
    public TestThread02(String url,String name) {
        this.url = url;
        this.name = name;
    }

    // 下载图片的执行体
    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url,name);
        System.out.println("下载的文件：" + name);
    }

    public static void main(String[] args) {
        TestThread02 t1 = new TestThread02("http://browser9.qhimg.com/bdm/768_474_0/t010824ab8b5cdfa138.jpg","str.png");
        TestThread02 t2 = new TestThread02("http://browser9.qhimg.com/bdm/768_474_0/t012468d019e3ce466b.jpg","str2.png");
        TestThread02 t3 = new TestThread02("http://browser9.qhimg.com/bdm/384_237_0/t01039b44f7c7ca5ca3.jpg","str3.png");

        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();

    }
}
// 下载器
class WebDownloader{
    // 下载方法
    public void downloader(String url,String name){
        try {
           FileUtils.copyURLToFile(new URL(url),new File(name));
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("IO异常，downloader下载方法异常");
        }
    }
}
