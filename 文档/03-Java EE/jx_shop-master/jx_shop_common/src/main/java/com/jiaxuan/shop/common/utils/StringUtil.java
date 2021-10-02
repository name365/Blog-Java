package com.jiaxuan.shop.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Author: jx
 * Date: 2019-03-10 10:25
 * Description: 文件重命名工具类
 */
public class StringUtil {

    public static String reFileName(String fileName) {
        if (fileName == null) {
            fileName = "default.jpg";
        }
        //取出"."号的位置
        int dotIndex = fileName.lastIndexOf(".");
        /*再截取 . 号之后的后缀*/
        String suffix = fileName.substring(dotIndex);
        //名称使用：年月日时分秒+100以内的随机数方式
        return new SimpleDateFormat("yyyyMMdddHHmmss").format(new Date())
                + new Random().nextInt(100)
                +suffix;
    }

    /**
     *功能描述: 根据时间和 uuid 生成唯一一个订单号
     * @Author jx
     * @Date 14:59 2019/03/24
     * @Param []
     * @return java.lang.String
     */
    public static String getOrderIdByUUId() {
        Date date=new Date();
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        String time = format.format(date);
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return time + String.format("%010d", hashCodeV);
    }

    public static void main(String[] args) {
        String orderNo = getOrderIdByUUId();
        System.out.println("StringUtil.main"+ orderNo);
    }
}
