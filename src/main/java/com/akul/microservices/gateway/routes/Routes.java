package com.akul.microservices.gateway.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Routes.java
 *
 * @author Andrii Kulynch
 * @version 1.0
 * @since 8/30/2025
 */

@Configuration
public class Routes {

    @Value("${gateway.product-service.uri}")
    private String productServiceUri;

    @Value("${gateway.order-service.uri}")
    private String orderServiceUri;

    @Value("${gateway.inventory-service.uri}")
    private String inventoryServiceUri;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product-service", r -> r.path("/api/v1/products/**")
                        .uri(productServiceUri))
                .route("order-service", r -> r.path("/api/v1/orders/**")
                        .uri(orderServiceUri))
                .route("inventory-service", r -> r.path("/api/v1/inventory/**")
                        .uri(inventoryServiceUri))
                .build();
    }
}
