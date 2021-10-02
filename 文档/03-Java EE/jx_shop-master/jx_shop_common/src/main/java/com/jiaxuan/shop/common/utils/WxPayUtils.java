package com.jiaxuan.shop.common.utils;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.*;

/**
 * Author: jx
 * Date: 2019-02-20 11:10
 * Description: 微信支付工具类
 */
public class WxPayUtils {
    
    /**
     *功能描述: XML 格式字符串转化为 map
     * @Author jx
     * @Date 11:11 2019/02/20
     * @Param [strXML] 
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    public static Map<String, String> xmlToMap(String strXML) throws Exception {
        try {
            Map<String, String> data = new HashMap<String, String>();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
            org.w3c.dom.Document doc = documentBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int idx = 0; idx < nodeList.getLength(); ++idx) {
                Node node = nodeList.item(idx);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                    data.put(element.getNodeName(), element.getTextContent());
                }
            }
            try {
                stream.close();
            } catch (Exception ex) {
                // do nothing
            }
            return data;
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    /**
     *功能描述: 将Map转换为XML格式的字符串
     * @Author jx
     * @Date 11:12 2019/02/20
     * @Param [data] 
     * @return java.lang.String
     */
    public static String mapToXml(Map<String, String> data) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder= documentBuilderFactory.newDocumentBuilder();
        org.w3c.dom.Document document = documentBuilder.newDocument();
        org.w3c.dom.Element root = document.createElement("xml");
        document.appendChild(root);
        for (String key: data.keySet()) {
            String value = data.get(key);
            if (value == null) {
                value = "";
            }
            value = value.trim();
            org.w3c.dom.Element filed = document.createElement(key);
            filed.appendChild(document.createTextNode(value));
            root.appendChild(filed);
        }
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        DOMSource source = new DOMSource(document);
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);
        String output = writer.getBuffer().toString(); //.replaceAll("\n|\r", "");
        try {
            writer.close();
        }
        catch (Exception ex) {
        }
        return output;
    }
    
    /**
     *功能描述: 生成微信支付 sign
     * @Author jx
     * @Date 13:32 2019/02/20
     * @Param [params, key] 
     * @return java.lang.String
     */
    public static String createSign(SortedMap<String, String> params, String key) throws Exception {
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<String, String>> es =  params.entrySet();
        Iterator<Map.Entry<String,String>> it =  es.iterator();

        //生成 stringA="appid=wxd930ea5d5a258f4f&body=test&device_info=1000&mch_id=10000100&nonce_str=ibuaiVcKdpRxkhJA";
        while (it.hasNext()){
            Map.Entry<String,String> entry = it.next();
            String k = entry.getKey();
            String v = entry.getValue();
            if(null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)){
                sb.append(k+"="+v+"&");
            }
        }

        sb.append("key=").append(key);
        String sign = CommonUtils.MD5(sb.toString()).toUpperCase();
        return sign;
    }
    
    /**
     *功能描述: 校验 支付 sign 是否正确
     * @Author jx
     * @Date 13:33 2019/02/20
     * @Param [params, key] 
     * @return boolean
     */
    public static boolean isCorrectSign(SortedMap<String, String> params, String key) throws Exception {
        String sign = createSign(params,key);
        String weixinPaySign = params.get("sign").toUpperCase();
        return weixinPaySign.equals(sign);
    }
    
    /**
     *功能描述: 获取有序 map
     * @Author jx
     * @Date 13:34 2019/02/20
     * @Param [map] 
     * @return java.util.SortedMap<java.lang.String,java.lang.String>
     */
    public static SortedMap<String,String> getSortedMap(Map<String,String> map){
        SortedMap<String, String> sortedMap = new TreeMap<>();
        Iterator<String> it =  map.keySet().iterator();
        while (it.hasNext()){
            String key  = it.next();
            String value = map.get(key);
            String temp = "";
            if( null != value){
                temp = value.trim();
            }
            sortedMap.put(key,temp);
        }
        return sortedMap;
    }
}
