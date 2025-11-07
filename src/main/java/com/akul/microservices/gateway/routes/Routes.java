package com.akul.microservices.gateway.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


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
                        .filters(f -> f.circuitBreaker(config -> config
                                .setName("productServiceCircuitBreaker")
                                .setFallbackUri("forward:/fallback/products")))
                        .uri(productServiceUri))
                .route("order-service", r -> r.path("/api/v1/orders/**")
                        .filters(f -> f.circuitBreaker(config -> config
                                .setName("orderServiceCircuitBreaker")
                                .setFallbackUri("forward:/fallback/orders")))
                        .uri(orderServiceUri))
                .route("inventory-service", r -> r.path("/api/v1/inventory/**")
                        .filters(f -> f.circuitBreaker(config -> config
                                .setName("inventoryServiceCircuitBreaker")
                                .setFallbackUri("forward:/fallback/inventory")))
                        .uri(inventoryServiceUri))
                .build();
    }

    //---- Leagucy in Spring Cloud MVC version

//    @Bean
//    public RouterFunction<ServerResponse> productServiceRoute() {
//        return GatewayRouterFunctions.route("product_service")
//                .route(RequestPredicates.path("/api/product"), HandlerFunctions.http("http://localhost:8080"))
//                .filter(circuitBreaker("productServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
//                .build();
//    }
//
//    @Bean
//    public RouterFunction<ServerResponse> productServiceSwaggerRoute() {
//        return GatewayRouterFunctions.route("product_service_swagger")
//                .route(RequestPredicates.path("/aggregate/product-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8080"))
//                .filter(circuitBreaker("productServiceSwaggerCircuitBreaker", URI.create("forward:/fallbackRoute")))
//                .filter(setPath("/api-docs"))
//                .build();
//    }
//
//    @Bean
//    public RouterFunction<ServerResponse> orderServiceRoute() {
//        return GatewayRouterFunctions.route("order_service")
//                .route(RequestPredicates.path("/api/order"), HandlerFunctions.http("http://localhost:8081"))
//                .filter(circuitBreaker("orderServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
//                .build();
//    }
//
//    @Bean
//    public RouterFunction<ServerResponse> orderServiceSwaggerRoute() {
//        return GatewayRouterFunctions.route("order_service_swagger")
//                .route(RequestPredicates.path("/aggregate/order-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8081"))
//                .filter(circuitBreaker("orderServiceSwaggerCircuitBreaker", URI.create("forward:/fallbackRoute")))
//                .filter(setPath("/api-docs"))
//                .build();
//    }
//
//    @Bean
//    public RouterFunction<ServerResponse> inventoryServiceRoute() {
//        return GatewayRouterFunctions.route("inventory_service")
//                .route(RequestPredicates.path("/api/inventory"), HandlerFunctions.http("http://localhost:8082"))
//                .filter(circuitBreaker("inventoryServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
//                .build();
//    }
//
//    @Bean
//    public RouterFunction<ServerResponse> inventoryServiceSwaggerRoute() {
//        return GatewayRouterFunctions.route("inventory_service_swagger")
//                .route(RequestPredicates.path("/aggregate/inventory-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8082"))
//                .filter(circuitBreaker("inventoryServiceSwaggerCircuitBreaker", URI.create("forward:/fallbackRoute")))
//                .filter(setPath("/api-docs"))
//                .build();
//    }
//
//    @Bean
//    public RouterFunction<ServerResponse> fallbackRoute() {
//        return route("fallbackRoute")
//                .GET("/fallbackRoute", request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).body("Service Unavailable, please try again later"))
//                .build();
//    }



}
