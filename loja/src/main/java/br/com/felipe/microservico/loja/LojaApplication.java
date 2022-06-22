package br.com.felipe.microservico.loja;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class LojaApplication {

    @Bean(name = "restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }

    @LoadBalanced
    @Bean(name = "restTemplateWithLoadBalance")
    RestTemplate restTemplateWithLoadBalance() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(LojaApplication.class, args);
    }

    /* Bean Global de Configuração do CircuitBreaker */

    @Bean(name = "defaultCustomizer")
    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id ->
                new Resilience4JConfigBuilder(id)

                        .timeLimiterConfig(TimeLimiterConfig.custom()
                                .timeoutDuration(Duration.ofSeconds(2))
                                .build())
                        .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())

                        .build());
    }

    @Bean(name = "customCircuitBreaker")
    Customizer<Resilience4JCircuitBreakerFactory> customCircuitBreaker() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(5)
                .waitDurationInOpenState(Duration.ofSeconds(2))
                .permittedNumberOfCallsInHalfOpenState(2)
//                .slowCallRateThreshold(2)
                .slidingWindowSize(2)
                .recordExceptions(IOException.class, TimeoutException.class)
//                .ignoreExceptions(BusinessException.class, OtherBusinessException.class)
                .build();
        CircuitBreakerRegistry circuitBreakerRegistry =
                CircuitBreakerRegistry.of(circuitBreakerConfig);
        return factory -> factory.configureCircuitBreakerRegistry(circuitBreakerRegistry);

    }

}
