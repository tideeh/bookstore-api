package com.example.bookstoreapi.utils.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Bookstore API with Java 17 and Spring Boot 3.2.5")
                .version("v1.0.0")
                .description("BOOKSTORE API")
                .termsOfService("https://google.com")
                .license(
                    new License()
                        .name("Apache 2.0")
                        .url("https://google.com/license")
                )
            );
    }
    
}