package com.ApiProject.ApiProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan("com.ApiProject.ApiProject.*")
@EntityScan("com.ApiProject.ApiProject.*")
@EnableJpaRepositories
public class ApiProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiProjectApplication.class, args);
//		SpringApplication app = new SpringApplication(ApiProjectApplication.class);
//		app.setDefaultProperties(Collections.singletonMap("server.port", "8090"));
//		app.run(args);
	}
}
