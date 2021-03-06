package org.appdynamics.deployment.service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.appdynamics.deployment.model.Controller;
import org.appdynamics.deployment.utils.StringUtils;

import com.mashape.unirest.http.Unirest;

public class RestClient {
	
	private String baseUrl;
	private String username;
	private String password;
	
	private RestClient(Controller controller) {
		super();
		
		if(controller.getUrl()!=null) {
			if(controller.getUrl().toLowerCase().endsWith("/controller"))
				this.baseUrl=controller.getUrl()+"/";
			else if(!controller.getUrl().toLowerCase().endsWith("/controller/")) {
				if(controller.getUrl().endsWith("/"))
					this.baseUrl=controller.getUrl()+"controller/";
				else
					this.baseUrl=controller.getUrl()+"/controller/";
			}
			else
				this.baseUrl=controller.getUrl();
		}

		this.username = controller.getUsername() + "@" + ((StringUtils.isNotBlank(controller.getAccount())?controller.getAccount():"customer1"));
		this.password = controller.getPassword();
	}
	
	public RestHttpRequest get(String uri) {
		return new RestHttpRequest(Unirest.get(baseUrl+uri).basicAuth(username, password));
	}
	
	public static void init() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
		boolean ignoreCookies = Boolean.parseBoolean(System.getProperty("appdynamics.ignoreCookies", "false"));
		boolean ignoreSSLCheck = Boolean.parseBoolean(System.getProperty("appdynamics.ignoreSSLCheck", "false"));
		
		HttpClientBuilder httpClientBuilder = HttpClients.custom();
		
		// Set JSON Object Mapper
		Unirest.setObjectMapper(RestHttpRequest.jsonMapper);
		
		// Http Request Config
		if(ignoreCookies) {
			
			RequestConfig.Builder globalConfig = RequestConfig.custom();
			
			// Disable Invalid cookie header
			globalConfig = globalConfig.setCookieSpec(CookieSpecs.IGNORE_COOKIES);
			
			httpClientBuilder = httpClientBuilder.setDefaultRequestConfig(globalConfig.build());
		}
				        
		// SSL Socket Factory
		if(ignoreSSLCheck) {
			
			// Disable SSL Checks
	        SSLContext sslContext = SSLContexts
	                .custom()
	                .loadTrustMaterial(new TrustStrategy() {
						@Override
						public boolean isTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {
							return true;
						}
	                })
	                .build();
	        
			HostnameVerifier hostnameVerifier = new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};
			
			org.apache.http.conn.ssl.SSLConnectionSocketFactory sslConFactory = new org.apache.http.conn.ssl.SSLConnectionSocketFactory(sslContext, hostnameVerifier);

			httpClientBuilder = httpClientBuilder.setSSLSocketFactory(sslConFactory);
		}
		
		// Set HTTP Client
		Unirest.setHttpClient(httpClientBuilder.build());
	}

	public static void shutdown() throws IOException {
		Unirest.shutdown();
	}

	public static RestClient of(Controller controller) {
		return new RestClient(controller);
	}
}
