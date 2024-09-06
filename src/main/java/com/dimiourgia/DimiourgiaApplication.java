package com.dimiourgia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DimiourgiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DimiourgiaApplication.class, args);
	}

}
