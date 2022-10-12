package org.personales.springgateway.config;

import org.personales.springgateway.model.TokenDto;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements GatewayFilter {

    //implement Logger
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(AuthFilter.class);
    @Autowired
    private WebClient.Builder webClient;

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        log.error(err);
        return response.setComplete();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);
        }
        String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
        String[] chunks = authorizationHeader.split(" ");
        if (chunks.length != 2 || !chunks[0].equals("Bearer")) {
            return onError(exchange, "Invalid authorization header", HttpStatus.UNAUTHORIZED);
        }
        String jwt = chunks[1];
        return webClient.build()
                .post()
                .uri("http://msvc-auth/auth/validate?token="+jwt)
                .retrieve()
                .bodyToMono(TokenDto.class)
                .map(tokenDto -> {
                    return exchange;
                }).flatMap(chain::filter);

    }
}
