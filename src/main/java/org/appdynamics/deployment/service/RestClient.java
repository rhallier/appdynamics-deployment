package org.appdynamics.deployment.service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
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
		// Set JSON Object Mapper
		Unirest.setObjectMapper(RestHttpRequest.jsonMapper);
		
		// Disable Invalid cookie header
		RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.IGNORE_COOKIES).build();
				        
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

		// Update HTTP Client configuration
		HttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(globalConfig).setSSLSocketFactory(sslConFactory).build();
		Unirest.setHttpClient(httpclient);
	}

	public static void shutdown() throws IOException {
		Unirest.shutdown();
	}

	public static RestClient of(Controller controller) {
		return new RestClient(controller);
	}
}
