package com.example.helloworld.services;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@Component
public class LogFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return chain.filter(exchange)
            .doOnEach(signal -> {
                if (signal.isOnComplete() || signal.isOnError()) {   final var response = exchange.getResponse();
                   // System.out.println(exchange.getRequest().getHeaders());
                    System.out.println(exchange.getRequest().getURI());
                    System.out.println(exchange.getRequest().getPath());
                }
            });
    }
}