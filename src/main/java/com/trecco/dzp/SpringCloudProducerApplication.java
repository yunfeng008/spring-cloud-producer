package com.trecco.dzp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//启用注册中心
@EnableDiscoveryClient
public class SpringCloudProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudProducerApplication.class, args);
	}

}
