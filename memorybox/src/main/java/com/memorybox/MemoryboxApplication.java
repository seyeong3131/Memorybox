package com.memorybox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MemoryboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemoryboxApplication.class, args);
	}

}
