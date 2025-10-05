package com.akul.microservices.gateway.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import reactor.core.publisher.Mono;

@Configuration
public class LoggingFilterConfig {

    @Bean
    public GlobalFilter logFilter() {
        return (ServerWebExchange exchange, GatewayFilterChain chain) -> {
            System.out.println(">>> Gateway Request URI: " + exchange.getRequest().getURI());
            System.out.println(">>> Gateway Request Method: " + exchange.getRequest().getMethod());
            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() ->
                            System.out.println("<<< Gateway Response Status: " + exchange.getResponse().getStatusCode())
                    ));
        };
    }
}

