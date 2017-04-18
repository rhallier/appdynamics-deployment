package org.appdynamics.deployment;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.annotation.PreDestroy;

import org.appdynamics.deployment.service.RestClient;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent>{
 
    public void onApplicationEvent(ContextRefreshedEvent contextStartedEvent) {
        System.out.println("On Startup");
		try {
			RestClient.init();
		} catch (IOException | KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
			e.printStackTrace(System.err);
		}
    }
    
    @PreDestroy
    public void onShutdown() {
        System.out.println("Context Stop Received");
        try {
    		RestClient.shutdown();
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
    }

}