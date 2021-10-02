package com.jiaxuan.shop.common.utils;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装http get post
 */
public class HttpUtils {

    private static  final Gson gson = new Gson();
    /**
     * get方法
     * @param url
     * @return
     */
    public static Map<String,Object> doGet(String url){

        Map<String,Object> map = new HashMap<>();
        CloseableHttpClient httpClient =  HttpClients.createDefault();
        //设置参数
        RequestConfig requestConfig =  RequestConfig.custom().setConnectTimeout(5000) //连接超时
                .setConnectionRequestTimeout(5000)//请求超时
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true)  //允许自动重定向
                .build();

        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);

        try{
           // 获取请求响应结果
           HttpResponse httpResponse = httpClient.execute(httpGet);
           if(httpResponse.getStatusLine().getStatusCode() == 200){
               String jsonResult = EntityUtils.toString(httpResponse.getEntity());
               //解决乱码
               jsonResult = new String(jsonResult.getBytes("ISO-8859-1"), "UTF-8");
               map = gson.fromJson(jsonResult,map.getClass());
           }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 封装post
     * @return
     */
    public static String doPost(String url, String data, int timeout){
        CloseableHttpClient httpClient =  HttpClients.createDefault();
        //超时设置
        RequestConfig requestConfig =  RequestConfig.custom().setConnectTimeout(timeout) //连接超时
                .setConnectionRequestTimeout(timeout)//请求超时
                .setSocketTimeout(timeout)
                .setRedirectsEnabled(true)  //允许自动重定向
                .build();

        HttpPost httpPost  = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        httpPost.addHeader("Content-Type","text/html;charset=UTF-8");

        if(data != null && data instanceof  String){ //使用字符串传参
            StringEntity stringEntity = new StringEntity(data,"UTF-8");
            httpPost.setEntity(stringEntity);
        }

        try{
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if(httpResponse.getStatusLine().getStatusCode() == 200){
                String result = EntityUtils.toString(httpEntity,"UTF-8");
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                httpClient.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
