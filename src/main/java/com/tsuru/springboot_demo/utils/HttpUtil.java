package com.tsuru.springboot_demo.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

	public static CloseableHttpClient getSslHttpClient(String file, String password)
			throws KeyStoreException, IOException, KeyManagementException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {

		// // Init keyStore
		// KeyStore keyStore = KeyStore.getInstance("PKCS12");// PKCS12、jks 等
		// FileInputStream instream = new FileInputStream(new File(file));//
		// 加载本地的证书进行https加密传输
		// try {
		// keyStore.load(instream, password.toCharArray());// 设置证书密码
		// } catch (CertificateException e) {
		// e.printStackTrace();
		// } catch (NoSuchAlgorithmException e) {
		// e.printStackTrace();
		// } finally {
		// instream.close();
		// }
		//
		// // Trust own CA and all self-signed certs, SSL protocol
		// SSLContext sslcontext =
		// SSLContexts.custom().loadTrustMaterial(keyStore, new
		// TrustSelfSignedStrategy()).build();
		//
		// SSLConnectionSocketFactory sslsf =
		// new SSLConnectionSocketFactory(sslcontext);
		InputStream instream = new FileInputStream(new File(file));
		BasicHttpClientConnectionManager connManager;
		KeyStore ks = KeyStore.getInstance("PKCS12");
		ks.load(instream, password.toCharArray());

		// 实例化密钥库 & 初始化密钥工厂
		KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		kmf.init(ks, password.toCharArray());

		// 创建 SSLContext
		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(kmf.getKeyManagers(), null, new SecureRandom());

		SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
				new String[] { "TLSv1" }, null, new org.apache.http.conn.ssl.DefaultHostnameVerifier());

		connManager = new BasicHttpClientConnectionManager(RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.getSocketFactory())
				.register("https", sslConnectionSocketFactory).build(), null, null, null);
		//return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		return HttpClientBuilder.create()
                .setConnectionManager(connManager)
                .build();
	}

	public static String doHttpsGet(String url) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		String result = null;
		try {
			// 通过址默认配置创建一个httpClient实例
			httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
			// 创建httpGet远程连接实例
			HttpGet httpGet = new HttpGet(url);
			// 设置请求头信息，鉴权
			// httpGet.setHeader("Authorization", "Bearer
			// da3efcbf-0845-4fe3-8aba-ee040be542c0");
			// 设置配置请求参数
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
					.setConnectionRequestTimeout(35000)// 请求超时时间
					.setSocketTimeout(60000)// 数据读取超时时间
					.build();
			// 为httpGet实例设置配置
			httpGet.setConfig(requestConfig);
			// 执行get请求得到返回对象
			response = httpClient.execute(httpGet);
			// 通过返回对象获取返回数据
			HttpEntity entity = response.getEntity();
			// 通过EntityUtils中的toString方法将结果转换为字符串
			result = EntityUtils.toString(entity, "utf-8");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			if (null != response) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != httpClient) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public static String doHttpsPost(String url, String data) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		String result = null;
		try {
			// 通过址默认配置创建一个httpClient实例
			httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
			// 创建httpPost远程连接实例
			HttpPost httpPost = new HttpPost(url);
			// 设置请求头信息，鉴权
			// httpGet.setHeader("Authorization", "Bearer
			// da3efcbf-0845-4fe3-8aba-ee040be542c0");
			// 设置配置请求参数
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
					.setConnectionRequestTimeout(35000)// 请求超时时间
					.setSocketTimeout(60000)// 数据读取超时时间
					.build();
			// 为httpPost实例设置配置
			httpPost.setConfig(requestConfig);

			httpPost.setEntity(new StringEntity(data, "utf-8"));

			// 执行get请求得到返回对象
			response = httpClient.execute(httpPost);
			// 通过返回对象获取返回数据
			HttpEntity entity = response.getEntity();
			// 通过EntityUtils中的toString方法将结果转换为字符串
			result = EntityUtils.toString(entity, "utf-8");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			if (null != response) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != httpClient) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public static String doSslHttpsPost(CloseableHttpClient httpClient, String url, String data) {
		CloseableHttpResponse response = null;
		String result = null;
		try {
			// 创建httpPost远程连接实例
			HttpPost httpPost = new HttpPost(url);
			// 设置请求头信息，鉴权
			// httpGet.setHeader("Authorization", "Bearer
			// da3efcbf-0845-4fe3-8aba-ee040be542c0");
			// 设置配置请求参数
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
					.setConnectionRequestTimeout(35000)// 请求超时时间
					.setSocketTimeout(60000)// 数据读取超时时间
					.build();
			// 为httpPost实例设置配置
			httpPost.setConfig(requestConfig);
			httpPost.addHeader("Content-Type", "text/xml");
//		    httpPost.addHeader("User-Agent", "wxpay sdk java v1.0 " + Constants.MCH_ID);
			httpPost.setEntity(new StringEntity(data, "utf-8"));

			// 执行get请求得到返回对象
			response = httpClient.execute(httpPost);
			// 通过返回对象获取返回数据
			HttpEntity entity = response.getEntity();
			// 通过EntityUtils中的toString方法将结果转换为字符串
			result = EntityUtils.toString(entity, "utf-8");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			if (null != response) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != httpClient) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

}
