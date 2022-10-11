package org.personales.consumersimpleexample;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class ConsumerSimpleExampleApplication {

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ConsumerSimpleExampleApplication.class);

//	@Bean
//	public Consumer<Long> consumer(){
//		return (number) -> {
//			logger.info("consumiendo la info {} ", number);
//		};
//	}

	// Implementando un processor que recibe la data de otro processor de otro microservicio y concatena la palabra
	// saludar
	/**
	 * processor-in-0
	 * processor-out-0
	 */
	@Bean
	public Function<Flux<String>, Flux<String>> processor(){
		return data -> data.map(value -> value + " saludar");
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumerSimpleExampleApplication.class, args);
	}

}
