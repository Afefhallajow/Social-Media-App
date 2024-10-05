package com.example.SocialMediaApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class SocialMediaApplication {


	public static void main(String[] args) {
		SpringApplication.run(SocialMediaApplication.class, args);
	}

}
