package com.devsu.ms.clientepersona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ClientepersonaApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ClientepersonaApplication.class, args);
	}

}
