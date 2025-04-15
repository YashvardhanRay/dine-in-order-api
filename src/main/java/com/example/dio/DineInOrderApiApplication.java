package com.example.dio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
@EnableCaching
public class DineInOrderApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DineInOrderApiApplication.class, args);
	}

}
