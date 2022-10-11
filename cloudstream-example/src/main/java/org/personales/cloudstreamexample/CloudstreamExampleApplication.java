package org.personales.cloudstreamexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class CloudstreamExampleApplication {

	private static final Logger logger = LoggerFactory.getLogger(CloudstreamExampleApplication.class);

	@Bean
	public Function<String, String> toUpperCase() {
		return data -> {
			logger.info("Received {} ", data);
			return data.toUpperCase();
		};
	}

	//create supplier flux
	/**
	 * producer-out-0
	 *
	 */
	//@Bean
	public Supplier<Flux<Long>> producer() {
		return () -> Flux.interval(Duration.ofSeconds(1)).log();
	}

	//create processor Flux
	/**
	 * processor-in-0
	 * processor-out-0
	 */
	//@Bean
	public Function<Flux<Long>, Flux<Long>> processor() {
		return data -> data.map(value -> value * value);
	}

	//create consumer Flux
	/**
	 * consumer-in-0
	 */
	//@Bean
	public Consumer<Long> consumer(){
		return (number) -> {
			logger.info("Received {} ", number);
		};
	}



	public static void main(String[] args) {
		SpringApplication.run(CloudstreamExampleApplication.class, args);
	}

}
