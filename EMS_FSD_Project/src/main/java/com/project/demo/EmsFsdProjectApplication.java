package com.project.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.project")
@EnableJpaRepositories(basePackages = "com.project.repository")
@EntityScan(basePackages = "com.project.model")
public class EmsFsdProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(EmsFsdProjectApplication.class, args);
	}
	
}
