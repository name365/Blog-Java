package com.jiaxuan.shop.common.utils;

import com.jiaxuan.shop.common.constant.ResponseStatusConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: jx
 * Date: 2019-03-09 20:31
 * Description: 响应数据封装类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult {

    /*状态码*/
    private int status;
    /*消息*/
    private String message;
    /*返回的数据*/
    private Object data;

    //成功
    public static ResponseResult success(){
       return new ResponseResult(ResponseStatusConstant.RESPONSE_STATUS_SUCCESS,"success",null);
    }

    //成功 带 message
    public static ResponseResult success(String message){
        return new ResponseResult(ResponseStatusConstant.RESPONSE_STATUS_SUCCESS,message,null);
    }

    //成功，带data
    public static ResponseResult success(Object data){
       return new ResponseResult(ResponseStatusConstant.RESPONSE_STATUS_SUCCESS,"success",data);
    }

    //成功，带data 和 message
    public static ResponseResult success(String message, Object data){
       return new ResponseResult(ResponseStatusConstant.RESPONSE_STATUS_SUCCESS,message,data);
    }

    //失败
    public static ResponseResult fail(){
        return new ResponseResult(ResponseStatusConstant.RESPONSE_STATUS_FAIL,"fail",null);
    }

    //失败 带消息
    public static ResponseResult fail(String message){
        return new ResponseResult(ResponseStatusConstant.RESPONSE_STATUS_FAIL,message,null);
    }

    //失败 带消息
    public static ResponseResult deny(String message){
        return new ResponseResult(ResponseStatusConstant.RESPONSE_STATUS_NO_PERMISSION,message,null);
    }
}
