package com.test.mortgage;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableCaching
@OpenAPIDefinition(
        info = @Info(
                title = "Mortgage Rate microservice REST API Documentation",
                description = "Calculate Mortgage with interest rate microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Bhavana",
                        email = "bhavanaagoswami@gmail.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description =  "Mortgage Rate microservice REST API Documentation",
                url = "http://localhost:8086/swagger-ui/index.html#/"
        )
)
public class MortgageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MortgageServiceApplication.class, args);
    }

}
