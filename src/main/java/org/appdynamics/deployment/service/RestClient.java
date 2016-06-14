package org.appdynamics.deployment.service;

import java.io.IOException;

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
	
	public static void init() throws IOException {
		Unirest.setObjectMapper(RestHttpRequest.jsonMapper);
	}

	public static void shutdown() throws IOException {
		Unirest.shutdown();
	}

	public static RestClient of(Controller controller) {
		return new RestClient(controller);
	}
}
