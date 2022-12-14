package org.personales.clientstandalone.clients;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "msvc-dragon-ball")
@LoadBalancerClient(name = "msvc-dragon-ball", configuration = LoadBalancerConfiguration.class)
public interface DragonBallCharacterClient {

    @GetMapping("/application-name")
    public ResponseEntity<String> getAplicationName();
}
