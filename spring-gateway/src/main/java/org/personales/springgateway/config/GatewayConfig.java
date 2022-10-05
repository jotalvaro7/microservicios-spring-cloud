package org.personales.springgateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class GatewayConfig {

    @Bean
    @Profile("localhostRouter-noEureka")
    public RouteLocator configLocalNoEureka(RouteLocatorBuilder builder) {
        return builder.routes().build();
    }

}
