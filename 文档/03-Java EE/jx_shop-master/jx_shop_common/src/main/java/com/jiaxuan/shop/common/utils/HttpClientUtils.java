package com.jiaxuan.shop.common.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HttpClientUtils工具类
 */
public class HttpClientUtils {

    /**
     * 执行get请求
     *
     * @param url
     * @param params
     * @return
     */
    public static String doGet(String url, Map<String, String> params) {
        // 创建httpclient
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String result = "";
        CloseableHttpResponse httpResponse = null;
        try {
            // 构建uri
            URIBuilder builder = new URIBuilder(url);
            // 构建参数
            if (params != null) {
                for (String key : params.keySet()) {
                    builder.addParameter(key, params.get(key));
                }
            }
            URI uri = builder.build();
            // 构建get请求
            HttpGet httpGet = new HttpGet(uri);
            // 执行
            httpResponse = httpClient.execute(httpGet);
            result = EntityUtils.toString(httpResponse.getEntity(), Charset.defaultCharset());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(httpClient, httpResponse);
        }
        return result;
    }

    /**
     * 执行get请求，没有参数
     *
     * @param url
     * @return
     */
    public static String doGet(String url) {
        return doGet(url, null);
    }

    /**
     * 执行post请求
     *
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, Map<String, String> params) {
        // 创建httpclient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        CloseableHttpResponse httpResponse = null;
        try {
            // 构建请求
            HttpPost httpPost = new HttpPost(url);
            // 构建参数
            if (params != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : params.keySet()) {
                    paramList.add(new BasicNameValuePair(key, params.get(key)));
                }
                // 模拟form表单entity //使用默认字符集
                HttpEntity formEntity = new UrlEncodedFormEntity(paramList, Charset.defaultCharset());
                httpPost.setEntity(formEntity);
            }
            // 执行
            httpResponse = httpClient.execute(httpPost);
            // 获取服务器返回的结果
            result = EntityUtils.toString(httpResponse.getEntity(), Charset.defaultCharset());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(httpClient, httpResponse);
        }
        return result;
    }

    /**
     * 执行post请求，没有参数
     *
     * @param url
     * @return
     */
    public static String doPost(String url) {
        return doPost(url, null);
    }

    /**
     * 执行post请求，参数为json字符串
     *
     * @param url
     * @param jsonParam
     * @return
     */
    public static String doPostJsonParam(String url, String jsonParam) {
        // 创建httpclient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        CloseableHttpResponse httpResponse = null;
        try {
            // 构建请求
            HttpPost httpPost = new HttpPost(url);

            if (!StringUtils.isEmpty(jsonParam)) {
                // 创建请求实体
                StringEntity stringEntity = new StringEntity(jsonParam, ContentType.APPLICATION_JSON);
                httpPost.setEntity(stringEntity);
            }

            // 执行
            httpResponse = httpClient.execute(httpPost);
            result = EntityUtils.toString(httpResponse.getEntity(), Charset.defaultCharset());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(httpClient, httpResponse);
        }
        return result;
    }

    /**
     * 关闭httpClient和httpResponse
     *
     * @param httpClient
     * @param httpResponse
     */
    private static void close(CloseableHttpClient httpClient, CloseableHttpResponse httpResponse) {
        if (httpResponse != null) {
            try {
                httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (httpClient != null) {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
