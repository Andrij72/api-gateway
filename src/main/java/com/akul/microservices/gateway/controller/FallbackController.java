package com.akul.microservices.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @RequestMapping(value = "/orders/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public Mono<String> ordersFallback() {
        return Mono.just("Order Service is currently unavailable. Please try again later.");
    }

    @RequestMapping(value = "/products/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public Mono<String> productsFallback() {
        return Mono.just("Product Service is currently unavailable. Please try again later.");
    }

    @RequestMapping(value = "/inventory/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public Mono<String> inventoryFallback() {
        return Mono.just("Inventory Service is currently unavailable. Please try again later.");
    }
}
