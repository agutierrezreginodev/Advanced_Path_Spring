package com.codeup.eventify.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI eventifyOpenAPI() {
        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080");
        localServer.setDescription("Development Server");

        Info info = new Info()
                .title("Eventify API")
                .version("1.0.0")
                .description(
                        "REST API for event and venue management. Allows you to create, read, update, and delete events and their locations.");

        return new OpenAPI()
                .info(info)
                .servers(List.of(localServer));
    }
}