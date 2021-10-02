package com.jiaxuan.front.shop.config;

/**
 * Author: jx
 * Date: 2019-04-02 14:35
 * Description:<描述>
 */
public class AliPayConfig {
    public static String app_id = "2016092800617299";//在后台获取（必须配置）

    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCtVef3WrmY974lPxsd4uvcofsq3QQ0ucjTmvMxg1Fy4+XIVZCgIQJqm935ndYw+Cm/+tgTKFcQM0mttDvt5LwPnaSux8myHUtiWr/VkPncrNcQ7IrMrkX/LNP8BEQjAiYn5ZqXa+3iLaegMvfWEJ4DnNMc0yCqPJ6SZkekagrdSQEj4ZaJ/BBM/4JUml7ax2qXHnKbfGSywu2TaNl19RC3dpjKqnDm+c4rnni3kcCVqc3swvUUzRmCGXAdpNTrQpTYZ9NEiKqbDzprK4VnCPAet5wjTn/IW4un8pYPu1IKlWJ4E1kHUc5/wU7QeVRFfRSw2yLZ+9xSLJFP5+xowk/NAgMBAAECggEARUzlUzXIomnacdXLJ6XEGw//9WEr/1CQBFodBRy1gbOv+OqMyhioeKsckmIScr2lI4ZMwUwNkwKOYI6Ar1oV/kBOyTpDX5zquMYDkIVIAHxx9+g6jh7XCXCIyGeEPmLrkmPv7L5P+2+5Zwk5oTI/mr4mgY2/r5BQ7cNKvWdQNKwE2M5G5HmFTNzCytbMmKsSaG2pXQqdZv/TXpI2/Bp583r6RuQoqrs/wnLsMYFMeoMITVdalCoMXdSBM3O1SwzG8CxbZUdbJEnT9P1zKZDwR0W75olS0BiT9ck6Ar6ElGPjqmB/P/o0tTyWmAn6G2R4BKLSy+L8bMQHu6Rb9Pik2QKBgQDcB+Ac8Zx4kj6YhacLiwaB2OcJvDQCgUM6WX/IbjsxEgPxygxUtvC0/MBRQiDBsdxk0Zqg2vabg/O90jasCNuURhfnEXBKEGARgL5covo+CfI6SHIYLKQgljS2JozQbF0RdakuNILilQ+9iSa0kM4I3kaEVi+5X7irOiJBY0IMMwKBgQDJq98WL3Ta3e0FAz6t2QxdMJEVQGYysbYn5gvzXVaWfLJlU/4d6wTzdA3ToDhyFTmeLKZOrl8JM070tpTctUhc5B2XLrX3kty50e2s8oYR7NmEXF0Gz3I0jDq9iheriwbR4Fqi4CjmGWH7EJCQ13XRXb1d3jGgdUWaO2f+rk4z/wKBgQDINKJHs9CcJPWv8my8pBOXsrUGtqjcXqG9RhJvXV2emLXeG+p8ark69YBIb9M+a4gOdGouUw5xEu+FmPEGOEkT8XNuXP+/ZIcFZg/QWrs4F/S/84tdU8FDpWjxFMKsIu3MJTN7R2yQnvFjlJgTVG8kwpuHbqSeF+sAYjUpKhe+4QKBgB+90HgCcib8pRCT6SqdreeA/GTW5P73n476E0lzmGxJJh7ysF1vHzFeQ8raPege1RnDN03M/iJJlBTKI/X1BnWwZcPk0zkrQVhx5THCXj22BKpjizTGM9h3dctdT8ptMF6aLEnUdDt7JUPJ4KcbcmzIj0SV80OpEZJr5TtdnetrAoGBAKsrXW7tt88LDjkJZj/KfkbBo3x5OEG9PYT0/WxGW2XPfVuHbJtJjBZNAYPq2Q+97P8n/9ROSNL7ZTwFL6cjSBZPQ3ksSnD7uo2q+VoSl49wYxFOXlpiTXUUlrDB5fHAkaMolEh6O537FCk09R1idO2FrGV3Qqt2xF0ERkk+uTjt";//教程查看获取方式（必须配置）

    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArVXn91q5mPe+JT8bHeLr3KH7Kt0ENLnI05rzMYNRcuPlyFWQoCECapvd+Z3WMPgpv/rYEyhXEDNJrbQ77eS8D52krsfJsh1LYlq/1ZD53KzXEOyKzK5F/yzT/AREIwImJ+Wal2vt4i2noDL31hCeA5zTHNMgqjyekmZHpGoK3UkBI+GWifwQTP+CVJpe2sdqlx5ym3xkssLtk2jZdfUQt3aYyqpw5vnOK554t5HAlanN7ML1FM0ZghlwHaTU60KU2GfTRIiqmw86ayuFZwjwHrecI05/yFuLp/KWD7tSCpVieBNZB1HOf8FO0HlURX0UsNsi2fvcUiyRT+fsaMJPzQIDAQAB";//教程查看获取方式（必须配置）

    /***
     *功能描述: 服务器异步通知页面路径  需 http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
    //public static String notify_url = "http://xiaomo.mynatapp.cc/front/pay/aliPayNotifyNotice";

    //部署服务器上，注意修改地址：
    public static String notify_url = "https://www.xmlvhy.com/xmShopFront/front/pay/aliPayNotifyNotice";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //public static String return_url = "https://xiaomo.mynatapp.cc/front/pay/aliPayReturnNotice";
    //部署服务器上注意修改地址
    public static String return_url = "https://www.xmlvhy.com/xmShopFront/front/pay/aliPayReturnNotice";


    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
}
