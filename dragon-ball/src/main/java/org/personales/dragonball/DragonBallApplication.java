package org.personales.dragonball;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DragonBallApplication {

	public static void main(String[] args) {
		SpringApplication.run(DragonBallApplication.class, args);
	}

}
