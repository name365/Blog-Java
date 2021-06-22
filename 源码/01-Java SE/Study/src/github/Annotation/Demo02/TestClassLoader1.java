package github.Annotation.Demo02;

/**
 * 类加载器
 * @author subeiLY
 * @create 2021-06-18 20:29
 */
public class TestClassLoader1 {
    public static void main(String[] args) throws ClassNotFoundException {
        // 获取系统类的加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        // 获取系统类加载器的父类加载器-->扩展类加载器    jre1.8.0_91\lib\ext
        ClassLoader parent = systemClassLoader.getParent();
        System.out.println(parent);

        // 获取扩展类加载器父类加载器-->根加载器(c/c++)  jre1.8.0_91\lib\rt.jar
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);

        // 测试当前类是哪个加载器加载的
        ClassLoader classLoader = Class.forName("github.Annotation.Demo02.TestClassLoader1").getClassLoader();
        System.out.println(classLoader);

        // 测试JDK内置的类是谁加载的
        classLoader = Class.forName("java.lang.Object").getClassLoader();
        System.out.println(classLoader);

        // 如何获得系统类加载器可以加载的路径
        System.out.println(System.getProperty("java.class.path"));

        // 双亲委派机制  检测安全性 你写的类和跟加载器一样的不会用你写的类
        // java.lang.String -->往上推

        /*
        F:\java\JDK\jre\lib\charsets.jar;
        F:\java\JDK\jre\lib\deploy.jar;
        F:\java\JDK\jre\lib\ext\access-bridge-64.jar;
        F:\java\JDK\jre\lib\ext\cldrdata.jar;
        F:\java\JDK\jre\lib\ext\dnsns.jar;
        F:\java\JDK\jre\lib\ext\jaccess.jar;
        F:\java\JDK\jre\lib\ext\jfxrt.jar;
        F:\java\JDK\jre\lib\ext\localedata.jar;
        F:\java\JDK\jre\lib\ext\nashorn.jar;
        F:\java\JDK\jre\lib\ext\sunec.jar;
        F:\java\JDK\jre\lib\ext\sunjce_provider.jar;
        F:\java\JDK\jre\lib\ext\sunmscapi.jar;
        F:\java\JDK\jre\lib\ext\sunpkcs11.jar;
        F:\java\JDK\jre\lib\ext\zipfs.jar;
        F:\java\JDK\jre\lib\javaws.jar;
        F:\java\JDK\jre\lib\jce.jar;
        F:\java\JDK\jre\lib\jfr.jar;
        F:\java\JDK\jre\lib\jfxswt.jar;
        F:\java\JDK\jre\lib\jsse.jar;
        F:\java\JDK\jre\lib\management-agent.jar;
        F:\java\JDK\jre\lib\plugin.jar;
        F:\java\JDK\jre\lib\resources.jar;
        F:\java\JDK\jre\lib\rt.jar;
        F:\java\IDEA2020.2\Study\out\production\Study;
        F:\java\JDK\jre\lib\commons-io-2.6.jar;
        F:\java\IDEA2020.2\IntelliJ IDEA 2020.2.2\lib\idea_rt.jar

         */
    }
}
