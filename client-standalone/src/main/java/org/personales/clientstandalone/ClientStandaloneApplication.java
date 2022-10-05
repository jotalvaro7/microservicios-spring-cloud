package org.personales.clientstandalone;

import org.personales.clientstandalone.clients.DragonBallCharacterClient;
import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;

@SpringBootApplication
@EnableFeignClients
public class ClientStandaloneApplication implements ApplicationRunner {


	@Autowired
	private EurekaClient eurekaClient;
	@Autowired
	private DragonBallCharacterClient dragonBallCharacterClient;
	private final static Logger log = LoggerFactory.getLogger(ClientStandaloneApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(ClientStandaloneApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		for (int i = 0; i < 10; i++) {
			ResponseEntity<String> responseEntity = dragonBallCharacterClient.getAplicationName();
			log.info("Status {}", responseEntity.getStatusCode());
			String body = responseEntity.getBody();
			log.info("Body {}", body);
		}
	}

	/**
	 * Implementacion de cliente de Eureka
	 * @param args
	 * @throws Exception
	 */
//	@Override
//	public void run(ApplicationArguments args) throws Exception {
//		Application application = eurekaClient.getApplication("dragon-ball");
//		log.info("Application name {}", application.getName());
//		List<InstanceInfo> instances = application.getInstances();
//		for (InstanceInfo instanceInfo :
//				instances) {
//			log.info("Ip address {}:{}", instanceInfo.getIPAddr(), instanceInfo.getPort());
//		}
//	}
}

