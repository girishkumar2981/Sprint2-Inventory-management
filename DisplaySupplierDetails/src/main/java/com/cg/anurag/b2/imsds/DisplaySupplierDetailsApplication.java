package com.cg.anurag.b2.imsds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
@EnableDiscoveryClient
@SpringBootApplication
public class DisplaySupplierDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisplaySupplierDetailsApplication.class, args);
	}
@Bean
public RestTemplate getRestTemplate() {
	return new RestTemplate();
}
}
