package github.base;

/**
 * @author subeiLY
 * @create 2021-05-25 9:45
 */
public class Demo04 {
    public static void main(String[] args) {
        int i = 128;
        byte b = (byte)i;   // 内存溢出
        double b1 = i;

        // 强制转换     (类型)变量名     高--低
        // 自动转换     低--高

        System.out.println(i);
        System.out.println(b);
        System.out.println(b1);

        /*
        注意点：
        1.不能对布尔类型转换
        2.不能将对象类型转换为不相干的类型
        3.在把高容量转换到低容量时，强制转换
        4.转换的时候可能存在内存溢出，或者精度问题！
         */

        System.out.println("++++++++++++++++++++++++++++++++++++");

        System.out.println((int)123.7);
        System.out.println((int)-45.89f);

        System.out.println("=======================");
        char a = 'a';
        int c = a+1;

        System.out.println(c);  // 98
        System.out.println((char)c);    // b

    }
}
