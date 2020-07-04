package com.acme.ideogo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// http://localhost:8080/swagger-ui/index.html?configUrl=/ideogo-api-docs/swagger-config#/user

@Configuration
public class OpenApiConfig {
    @Bean(name = "ideogoOpenApi")
    public OpenAPI ideogoOpenApi() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("IdeoGo Application API").description(
                        "IdeoGo API implemented with Spring Boot RESTful service and documented using springdoc-openapi and OpenAPI 3."));
    }
}
