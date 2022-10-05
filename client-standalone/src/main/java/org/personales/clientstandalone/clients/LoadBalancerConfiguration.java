package org.personales.clientstandalone.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBalancerConfiguration {
    private static final Logger log = LoggerFactory.getLogger(LoadBalancerConfiguration.class);

    /**
     * TODO Esta configuracion permite que el balanceo de carga no este definido por un balanceador que define el trafico
     * TODO para todos los clientes, si no que cada cliente puede definir su propia estrategia de balanceo de carga utilizando
     * TODO feing
     */

    /**
     * Configuración de Balanceo de carga por parte del cliente utilizando Feing
     * Indicando que un mismo nodo recibirá todas las peticiones
     * @param context
     * @return
     */
//    @Bean
//    public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(ConfigurableApplicationContext context){
//        log.info("Configuring Load Balancer To Prefer Same Instance");
//        return ServiceInstanceListSupplier
//                .builder()
//                .withBlockingDiscoveryClient()
//                .withSameInstancePreference()
//                .build(context);
//    }

    /**
     * Configuración de Balanceo de carga por parte del cliente utilizando Feing
     * Indicando que diferentes nodos reciban las peticiones
     * @param context
     * @return
     */
    @Bean
    public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(ConfigurableApplicationContext context){
        log.info("Configuring Load Balancer To Prefer Same Instance");
        return ServiceInstanceListSupplier
                .builder()
                .withBlockingDiscoveryClient()
                .build(context);
    }
}
