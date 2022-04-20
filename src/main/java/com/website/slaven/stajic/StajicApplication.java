package com.website.slaven.stajic;


import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class StajicApplication extends SpringBootServletInitializer {


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(StajicApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(StajicApplication.class, args);
	}




}
