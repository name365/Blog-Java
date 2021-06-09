package github.struct;

import java.util.Scanner;

/**
 * @author subeiLY
 * @create 2021-05-26 14:48
 */
public class IFDemo04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //
        char grade = 'K';
        switch (grade){
            case 'A':
                System.out.println("优秀");
                break;
            case 'B':
                System.out.println("良好");
                break;
            case 'C':
                System.out.println("及格");
                break;
            case 'D':
                System.out.println("再接再厉");
                break;
            case 'E':
                System.out.println("挂科");
                break;
            default:
                System.out.println("成绩错误！！！");
        }

        scanner.close();
    }
}
