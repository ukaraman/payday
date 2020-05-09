package com.payday.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BasicserviceApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(BasicserviceApplication.class, args);
	}
}