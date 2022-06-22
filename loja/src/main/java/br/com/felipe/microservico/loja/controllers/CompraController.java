package br.com.felipe.microservico.loja.controllers;


import br.com.felipe.microservico.loja.dtos.CompraDTO;
import br.com.felipe.microservico.loja.models.Compra;
import br.com.felipe.microservico.loja.services.CompraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/compra")
public class CompraController {


    private final Logger log = LoggerFactory.getLogger(CompraController.class);

    @Qualifier("defaultCustomizer")
    private final CircuitBreakerFactory circuitBreakerFactory;

    @Qualifier("customCircuitBreaker")
    private final CircuitBreaker customCircuitBreaker;

    private final CompraService compraService;

    public CompraController(
            CircuitBreakerFactory circuitBreakerFactory,
            CircuitBreakerFactory customCircuitBreakerFactory,
            CompraService compraService
    ) {
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.compraService = compraService;
        this.customCircuitBreaker = customCircuitBreakerFactory.create("id");
    }

    @GetMapping(value = "testeCustomCircuit={seconds}")
    public Map testeCustom(@PathVariable int seconds) {

//        log.info("testing a custom circuit breaker...");

        return customCircuitBreaker.run(compraService.delaySuppplier(seconds), t -> {
            log.warn("delay call failed error");
            return fallbackMethod(1);
        });

    }

    @GetMapping(value = "/testeCircuit={seconds}")
//    @Retry(fallbackMethod = "fallbackMethod", name = "loja")
    public Map testeCircuitBreaker(@PathVariable int seconds) {

//        Retry retry = Retry.ofDefaults("loja");
//        Supplier<Map> mapSupplier = compraService.delaySuppplier(seconds);
//        Supplier<Map> decoratedSupplier = Decorators.ofSupplier(decoratedSupplier)
//                .withCircuitBreaker(circuitBreaker)
//                .withBulkhead(bulkhead)
//                .withRetry(retry)
//                .decorate();
//        Supplier<Map> mapSupplier
//        RetryConfig config = RetryConfig.custom()
//                .maxAttempts(2)
//                .waitDuration(Duration.ofMillis(1000))
//                .retryOnResult(response -> response.() == 500)
//                .failAfterMaxAttempts(true)
//                .build();
//        CircuitBreaker circuitBreaker = circuitBreakerFactory.create()

        return circuitBreakerFactory.create("delay")

                .run(compraService.delaySuppplier(seconds), t -> {
                    log.warn("delay call failed error", t);
                    return fallbackMethod(1);
//            Map<String, String> fallback = new HashMap<>();
//            fallback.put("hello", "world");
//            return fallback;
                });
    }


    @PostMapping(value = "/testeCircuit")
    public Compra realizaCompraCircuit(@RequestBody CompraDTO compra) {

        log.info("realizaCompraCircuit");
        return circuitBreakerFactory.create("dela2y").run(compraService.realizaCompraCircuirt(compra), t -> {
            log.warn("delay call failed error", t);
            return new Compra(-1L, -1, "Erro circuite braker");
        });
    }

    @PostMapping()
    public Compra realizaCompra(@RequestBody CompraDTO compra) {


        return compraService.realizaCompra(compra);

//        return ResponseEntity
//                .status(201)
//                .body(compra);


    }

    public Map fallbackMethod(int seconds) {
        Map<String, String> fallback = new HashMap<>();
        fallback.put("falckbackMethodo", "erro");
        if (1 == 1)
            throw new RuntimeException("aeae");
        return fallback;
//        return circuitBreakerFactory.create("delay").run(compraService.delaySuppplier(seconds), t -> {
//            log.warn("delay call failed error"    , t);
//            Map<String, String> fallback = new HashMap<>();
//            fallback.put("hello", "world");
//            return fallback;
//        });
    }

}
