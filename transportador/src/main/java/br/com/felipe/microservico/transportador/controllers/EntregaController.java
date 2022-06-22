package br.com.felipe.microservico.transportador.controllers;

import br.com.felipe.microservico.transportador.dtos.EntregaDTO;
import br.com.felipe.microservico.transportador.dtos.VoucherDTO;
import br.com.felipe.microservico.transportador.services.EntregaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entrega")
public class EntregaController {

    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @PostMapping
    public VoucherDTO reservaEntrega(@RequestBody EntregaDTO pedidoDTO) {
        return entregaService.reservaEntrega(pedidoDTO);
    }
}
