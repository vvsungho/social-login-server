package com.study.sociallogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SocialLoginApplication {
	public static void main(String[] args) {
		SpringApplication.run(SocialLoginApplication.class, args);
	}

}
