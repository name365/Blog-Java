package github.base;

/**
 * @author subeiLY
 * @create 2021-05-25 9:17
 */
public class Demo03 {
    public static void main(String[] args) {
        // 整数拓展：    进制      二进制0b   十进制     八进制0    十六进制0x

        int i=10;
        int i2 = 010;   // 八进制0
        int i3 = 0x10;  // 十六进制0x

        System.out.println(i);  // 10
        System.out.println(i2); // 8
        System.out.println(i3); // 16

        System.out.println("-------------------------------------");
        //************************************************
        // 浮点数拓展
        // BigDecimal   数据工具类
        // float 有限     离散      舍入误差    大约  接近但不等于
        // 最好完全使用浮点数进行比较

        float f = 0.1f; // 0.1
        double d = 1.0/10;  // 0.1

        System.out.println(f==d);   // false

        float d1 = 2425444564215654564f;
        float d2 = d1 + 1;

        System.out.println(d1==d2); // true

        System.out.println("-------------------------------------");
        //************************************************
        // 字符拓展
        char c1 = 'a';
        char c2 = '中';

        System.out.println(c1);
        System.out.println((int)c1);    // 强制转换

        System.out.println(c2);
        System.out.println((int)c2);    // 强制转换

        // 所有字符的本质还是数字
        // 编码 Unicode   2字节    0 - 65536   Excel

        char c3 = '\u0061';

        System.out.println(c3); // a

        // 转义字符
        // \t   制表符
        // \n   换行
        // ......

        System.out.println("hello\tworld!");
        System.out.println("hello\nworld!");

        System.out.println("-------------------------------------");

        //
        String sa = new String("hello world");
        String sb = new String("hello world");
        System.out.println(sa==sb);     // false

        String sc = "hello world";
        String sd = "hello world";
        System.out.println(sc==sd);     // true

        // 布尔值扩展
        boolean flag = true;

        if(flag==true){}    // 新手
        if(flag){}      // 老油条

        // 代码要精简易读
    }
}
