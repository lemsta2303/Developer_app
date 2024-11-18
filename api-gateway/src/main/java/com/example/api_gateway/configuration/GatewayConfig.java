package com.example.api_gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("category-management", r -> r
                        .path("/api/projects/**")
                        .uri("http://localhost:8081"))

                .route("material-management", r -> r
                        .path("/api/materials/**")
                        .uri("http://localhost:8082"))

                .build();
    }


}
