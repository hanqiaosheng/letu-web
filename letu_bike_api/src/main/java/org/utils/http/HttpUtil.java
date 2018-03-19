package org.utils.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	/**
	 * Http请求
	 * 
	 * @param url
	 * @param params
	 * @param isGet
	 * @param useSsl
	 * @return
	 */
	public static String requestForString(String url, String params,String partner,
			boolean isGet, boolean useSsl) {
		System.out.println("requestForString url=" + url + " params=" + params);
		HttpRequestBase httpRequest;
		if (isGet) {
			if (!StringUtils.isEmpty(params)) {
				if (url.indexOf("?") == -1) {
					url += "?" + params;
				} else {
					url += "&" + params;
				}
			}
			httpRequest = new HttpGet(url);
		} else {
			HttpPost httpPost = new HttpPost(url);
			if (!StringUtils.isEmpty(params)) {
				httpPost.setEntity(new StringEntity(params, "UTF-8"));
			}
			httpRequest = httpPost;
		}
		return execute(httpRequest, useSsl,partner);
	}

	/**
	 * 执行
	 * 
	 * @param httpRequest
	 * @param useSsl
	 * @return
	 */
	private static String execute(HttpRequestBase httpRequest, boolean useSsl,String partner) {
		long start = System.currentTimeMillis();
		HttpClient httpClient = null;
		HttpEntity entity = null;
		try {
			if (!useSsl) {
				httpClient = HttpClientManager.getHttpClient();
			} else {
				// 指定读取证书格式为PKCS12
				KeyStore keyStore = KeyStore.getInstance("PKCS12");
				// 读取本机存放的PKCS12证书文件
				FileInputStream instream = new FileInputStream(new File(Thread.currentThread().getContextClassLoader().getResource("apiclient_cert.p12").getPath()));
				try {
					// 指定PKCS12的密码(商户ID)
					keyStore.load(instream, partner
							.toCharArray());
				} finally {
					instream.close();
				}
				SSLContext sslcontext = SSLContexts
						.custom()
						.loadKeyMaterial(keyStore,
								partner.toCharArray())
						.build();
				// 指定TLS版本
				SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
						sslcontext,
						new String[] { "TLSv1" },
						null,
						SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
				// 设置httpclient的SSLSocketFactory
				httpClient = HttpClients.custom().setSSLSocketFactory(sslsf)
						.build();
			}
			HttpResponse response = httpClient.execute(httpRequest);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				// TODO
			}
			entity = response.getEntity();
			String str = EntityUtils.toString(entity, "UTF-8");
			System.out.println("url[" + httpRequest.getURI().toString() + "]请求返回[" + str
					+ "]");
			return str;
		} catch (Exception e) {
			httpRequest.abort();
		} finally {
			if (null != entity) {
				try {
					EntityUtils.consume(entity);
				} catch (IOException e) {
				}
			}

			System.out.println("请求url[" + httpRequest.getURI() + "]耗时"
					+ (System.currentTimeMillis() - start));
		}
		return "{}";
	}
}
