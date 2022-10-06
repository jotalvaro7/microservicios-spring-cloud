package org.personales.gameofthrones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GameOfThronesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameOfThronesApplication.class, args);
	}

}
