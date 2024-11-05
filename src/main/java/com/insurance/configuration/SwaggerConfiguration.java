package com.insurance.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {

	@Bean
	OpenAPI customApi() {
		return new OpenAPI().info(new Info().title("Insurance Project").version("1.0")
				.description("API Documentation for Project")
				.contact(new Contact().name("Tejas Gire | Mahesh Diwan | Ritu Dhakad").email("giretejas@gmail.com")));
	}
}
