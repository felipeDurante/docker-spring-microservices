package br.com.felipe.microservico.transportador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TransportadorApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransportadorApplication.class, args);
    }

}
