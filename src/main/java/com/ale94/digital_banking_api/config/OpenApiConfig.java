package com.ale94.digital_banking_api.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Digital Banking API",
                version = "1.0",
                description = "Documentation for endpoints in Digital Banking"
        )
)
public class OpenApiConfig {

}
