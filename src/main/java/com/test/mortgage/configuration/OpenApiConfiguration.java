package com.test.mortgage.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * open api configuration.
 */
@Configuration
public class OpenApiConfiguration {

    /**
     * Adding open api document.
     * @return OpenApi.
     */
    @Bean
    public OpenAPI customeOpenApi() {
        return new OpenAPI()
                .info(new Info()
                .title("Mortgage API")
                .version("1.0")
                .description("API for interest rate API and Mortgage check API"));
    }
}
