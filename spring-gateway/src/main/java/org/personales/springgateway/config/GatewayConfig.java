package org.personales.springgateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class GatewayConfig {

    @Bean
    @Profile("localhost-noEureka")
    public RouteLocator configLocalNoEureka(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/v1/dragonball/*")
                        .uri("http://localhost:8082"))
                .route(r -> r.path("/api/v1/gameofthrones/*")
                        .uri("http://localhost:8083"))
                .build();
    }


    @Bean
    @Profile("localhost-eureka")
    public RouteLocator configLocalEureka(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/v1/dragonball/*")
                        .uri("lb://dragon-ball"))
                .route(r -> r.path("/api/v1/gameofthrones/*")
                        .uri("lb://game-of-thrones"))
                .build();
    }

    @Bean
    @Profile("localhost-eureka-circuitBreaker")
    public RouteLocator configLocalEurekaCircuitBreaker(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/v1/dragonball/*")
//                            .filters(f -> f.circuitBreaker(config -> config.setName("failoverCB")
//                                .setFallbackUri("forward:/api/v1/db-failover/dragonball/characters")
//                                .setRouteId("dbFailover")))
                        .uri("lb://dragon-ball"))
                .route(r -> r.path("/api/v1/gameofthrones/*")
                        .uri("lb://game-of-thrones"))
                .route(r -> r.path("/api/v1/db-failover/dragonball/*")
                        .uri("lb://dragon-ball-failover"))
                .build();
    }

}
