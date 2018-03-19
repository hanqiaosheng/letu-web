package org.util.weixin;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.utils.http.HttpClientConnectionManager;

/**
 * This example demonstrates how to create secure connections with a custom SSL
 * context.
 */
public class ClientCustomSSL {

    public static String doRefund(String url,String data,String partner,String certificate) throws Exception {
    	/**
    	 * 注意PKCS12证书 是从微信商户平台-》账户设置-》 API安全 中下载的
    	 */
    	
    	String path = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
//		path = path.replace("letu_bike_api", "letu_bike_consumer_weixin");
		System.out.println(path);
        KeyStore keyStore  = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(new File(path+certificate));//P12文件目录
        try {
        	/**
        	 * 此处要改
        	 * */
            keyStore.load(instream, partner.toCharArray());//这里写密码..默认是你的MCHID
        } finally {
            instream.close();
        }

        // Trust own CA and all self-signed certs
        /**
    	 * 此处要改
    	 * */
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, partner.toCharArray())//这里也是写密码的  
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        try {
        	HttpPost httpost = HttpClientConnectionManager.getPostMethod(url);  //模拟浏览器post提交
        	httpost.setEntity(new StringEntity(data, "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(httpost);
            try {
                HttpEntity entity = response.getEntity();
                String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
                EntityUtils.consume(entity);
               return jsonStr;
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }
    
    public static String doRefundApp(String url,String data) throws Exception {
    	/**
    	 * 注意PKCS12证书 是从微信商户平台-》账户设置-》 API安全 中下载的
    	 */
    	String path = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
//		path = path.replace("sunny_bike_api", "sunny_bike_consumer_weixin");
		System.out.println(path);
        KeyStore keyStore  = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(new File(path+"apiclient_cert2.p12"));//P12文件目录
        try {
        	/**
        	 * 此处要改
        	 * */
            keyStore.load(instream, "1484808752".toCharArray());//这里写密码..默认是你的MCHID
        } finally {
            instream.close();
        }

        // Trust own CA and all self-signed certs
        /**
    	 * 此处要改
    	 * */
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, "1484808752".toCharArray())//这里也是写密码的  
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        try {
        	HttpPost httpost = HttpClientConnectionManager.getPostMethod(url);  //模拟浏览器post提交
        	httpost.setEntity(new StringEntity(data, "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(httpost);
            try {
                HttpEntity entity = response.getEntity();
                String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
                EntityUtils.consume(entity);
               return jsonStr;
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }
    
    

    /**
     * 查询订单
     * @param url
     * @param data
     * @return
     * @throws Exception
     */
    public static String orderQuery(String url,String data) throws Exception {
    	CloseableHttpClient httpClient = getHttpClient();
     	HttpPost httpost = HttpClientConnectionManager.getPostMethod(url);  //模拟浏览器post提交
     	httpost.setEntity(new StringEntity(data, "UTF-8"));
     	CloseableHttpResponse response = httpClient.execute(httpost);
         try {
             HttpEntity entity = response.getEntity();
             String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
             EntityUtils.consume(entity);
             return jsonStr;
         } finally {
             response.close();	
         }
    }
    
    private static CloseableHttpClient getHttpClient(){
        return HttpClients.createDefault();
    }
}
