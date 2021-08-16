package cn.smbms.tools;

import java.math.BigDecimal;

/**
 * Created by user on 2015/7/6.
 */
public class BigDecimalUtil {

    /**
     * BigDecimalOprations + - * /
     */
    enum BigDecimalOprations{
        add,subtract,multiply,divide
    }

    /**
     * OperationASMD + - * / add substract multiiply divide
     * @param numOne [String Integer Long Double Bigdecimal]
     * @param numTwo [String Integer Long Double Bigdecimal]
     * @param bigDecimalOpration
     * @param scale
     * @param roundingMode
     * @return
     * @throws Exception
     */
    public static BigDecimal OperationASMD(Object numOne,Object numTwo,BigDecimalOprations bigDecimalOpration,int scale,int roundingMode) throws Exception{
        BigDecimal num1 = new BigDecimal(String.valueOf(numOne)).setScale(scale,roundingMode);
        BigDecimal num2 = new BigDecimal(String.valueOf(numTwo)).setScale(scale,roundingMode);
        switch (bigDecimalOpration){
            case add: return num1.add(num2).setScale(scale,roundingMode);
            case subtract: return num1.subtract(num2).setScale(scale,roundingMode);
            case multiply: return num1.multiply(num2).setScale(scale,roundingMode);
            case divide: return num1.divide(num2, scale, roundingMode);
        }
        return null;
    }


    /* Code Demo Exp */
    public static void main(String[] args){
        try {
            System.out.println(BigDecimalUtil.OperationASMD(36.23,23.369,BigDecimalOprations.add,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD("36.23","23.369",BigDecimalOprations.add,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD(36,23,BigDecimalOprations.add,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD(36l,69l,BigDecimalOprations.add,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD(new BigDecimal(0.2635),new BigDecimal(2.3568),BigDecimalOprations.add,2,BigDecimal.ROUND_DOWN));


            System.out.println(BigDecimalUtil.OperationASMD(36.23,23.369,BigDecimalOprations.subtract,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD("36.23","23.369",BigDecimalOprations.subtract,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD(36,23,BigDecimalOprations.subtract,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD(36l,69l,BigDecimalOprations.subtract,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD(new BigDecimal(0.2635),new BigDecimal(2.3568),BigDecimalOprations.subtract,2,BigDecimal.ROUND_DOWN));


            System.out.println(BigDecimalUtil.OperationASMD(36.23,23.369,BigDecimalOprations.multiply,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD("36.23","23.369",BigDecimalOprations.multiply,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD(36,23,BigDecimalOprations.multiply,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD(36l,69l,BigDecimalOprations.multiply,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD(new BigDecimal(0.2635),new BigDecimal(2.3568),BigDecimalOprations.multiply,2,BigDecimal.ROUND_DOWN));


            System.out.println(BigDecimalUtil.OperationASMD(36.23,23.369,BigDecimalOprations.divide,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD("36.23","23.369",BigDecimalOprations.divide,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD(36,23,BigDecimalOprations.divide,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD(36l,69l,BigDecimalOprations.divide,2,BigDecimal.ROUND_DOWN));
            System.out.println(BigDecimalUtil.OperationASMD(new BigDecimal(0.235),new BigDecimal(0.5689),BigDecimalOprations.divide,2,BigDecimal.ROUND_DOWN));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }







}