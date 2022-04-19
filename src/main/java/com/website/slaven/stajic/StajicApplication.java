package com.website.slaven.stajic;


import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class StajicApplication {

	public static void main(String[] args) {
		SpringApplication.run(StajicApplication.class, args);
	}

}
