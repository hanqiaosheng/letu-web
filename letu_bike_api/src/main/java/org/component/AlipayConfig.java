package org.component;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：1.0
 *日期：2016-06-06
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	//商户的seller_id
	public static String seller_id = "hwg@letulife.com";
    //支付宝分配给开发者的应用Id  如：2014072300007148
	public static String app_id = "2017062607571905";
	//合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://openhome.alipay.com/platform/keyManage.htm?keyType=partner
	public static String partner = "2088721288913621";

	//商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
	public static String private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCQs+lCMvyq2q3aeqISpRr3OICEPoH7K/KQZgVY1A/ySQSm3q1roI3vHXSNcyf7jmQp6blPrVgo+lMUYCmNDoXRiy3mqDkA6Gl/kyuWxAjLDbzCTanQQ6gPWG9EZiO5Q586jWoz/nsWP3eelxb5Iu5II/WQHH5icbLwg80kvTFxhscLj+VVLODGo6q1vtSgF+plocnSOyhpRHHfvfkSWV+o48Z1d03I1SsTOHgeNTKN8WvibxXe4BHh91TMQGG3u1GAc5Z5NoLb+hCjLb/VmhK8GOigXRJkABaPSn+R+qqWLVrFhmv99PrZxU6mSoCzAvXJGxNuRd7JllcV7+soO17PAgMBAAECggEAVDB4vE1R+E327LCA73wkLkYvb8G2el5daGoTHlVzWbHmBPh1ybLGIaLvlwSy9Icp+8DCUns2WdEa2TYBttIGRZ5GfHbPg5m1F8aWi8dOnmVFt37cGBHlC9iWFCOhET8ouzZf3hRnpNAUVq7tbNJH9K0HSGtrlPc19v2YM5Hn3HZ/wGr/oySYSR0u7N0mI/wIOL/nzbICPSrLWTbkml8ZPkvl59VFyL0VHB+/y3T2u5d2sO7IT0+4OkjtxlTMuD2X+a94xCYzHWwP1Z1RjX9InItG9SuYhT7rbZAKJ0lhry2tONKrxVxlwf3tcpRf8nWwF1OyW2xmX4glHCWh8sxjsQKBgQDKvaW7Ld3PG+fnjPxKsChOi/2PwIEm/E37xZEjiIpTxx32xDY8RLhB05yIFJqB9BxBn/tniAvgl2wS+laMMGUFofrbt321GgNdLYap4NGrUKVGGXtWzosAquypJSFc6RMTxI1lhLD1ywdbyfJJKXjzmCAF+GXXE8Fx/a/h2rMyPQKBgQC2tzJWo4OTSXP2wwEC6eRvfjl8Z63JaR2PObqNQv4qvjHnPxjT+gQjULy3H2R0Mvz9aIJ4VQeEOI5z1+KJKHfMEY3xPFL8b2AIg1rLfmzQBeX9KUKCM482HYukPJ6XPjh5QrBZr2suVPUxtm0DUxePZ3VeOIg2ydXiXe9m7oBh+wKBgEt6pYv1PIIRwpm7LGZmBOuRRLpTEgJApovYdvuCpvUfyXEIhaSUdQDU1o73FLrS9f4coI1f4gQge7TpDHWuwr2BG5LXUm6tG5mCDIOMVqLwKEfbzsZ3qNp7yBYE49HiWB7g6z9jUjp/9uJeYdyJGyWdejaeztNu4HMe3m84a8SFAoGAdggiHJXEiYEJyH5wSNGPH/cKa7MOMO4Wik9gk6CTUElNM3UvNMBtq+KcBi/hVK8bfxfkMJe9I0zluS/cx3285bbxWsT9VtUb1GQGYy5oXHly+PeOX02vVeYALzh5unoXQT4zbbxEIMoBGvwdr+gdcCYFS7h5Retcn77OyE+J38ECgYAxG3/l/AXnuC8ncsGEd4BeRpUOTj8696gHqaTGns0xw/swqjWxqEzuYlp98/YoWF2M7BXG0UIRUNFwv7hJvcA2yfhbyH5Cbl7mcBuUlpWhzP7sbTZC+tv3MR1+xAZL1gXx35CQ2XoQcr+WnExJU6MDjkwA9oglQlFlEMRzMnjCnw==";
	
	//支付宝的公钥，查看地址：https://openhome.alipay.com/platform/keyManage.htm?keyType=partner
	public static String alipay_public_key  = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqvidwSCh73HXynAF68TzgHDfjoBLz/0NDScaPRm/w9yx0Ilz7UA5J+UDgZM4yJItslkpKcHzyqTgYu1Df8jU0FHm+qJ+vNnF/KlGzw/tLk2lwCiZ5Z/t5C2qF0KdFk+C7DumSmHH1gvMGsLv10Eb0MdUdbNBWzbmwkHPFbkC5JWX219UYrB3JcsGfVesxQAegY35cqH50EKjt9yPyRVaCljVCx9GWz/rDMxp6efJ9s//DcJv4Uvi+Gvc6JFG0AdrUOKnoo2F9+7heMVKlQpZj5zjYXDD8pN+jHuuRoH6pRCV1P+m6G3Qt9ybn98pAu4wtEuVnOh8ws/fYbrRMBTubwIDAQAB";
	
	// 签名方式
	public static String sign_type = "RSA2";
	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path ="C://";
		
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";

	// 接收通知的接口名
	public static String service = "mobile.securitypay.pay";
	
	//异步通知回调地址  "http://panchenri.6655.la:32605/star_consumer_app/ali/verify"
	//public static String notify_url = "http://wechat.newbike.com.cn/ali/verify.action";
//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
}

