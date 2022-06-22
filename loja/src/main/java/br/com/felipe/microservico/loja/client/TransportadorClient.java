package br.com.felipe.microservico.loja.client;

import br.com.felipe.microservico.loja.DTO.InfoEntregaDTO;
import br.com.felipe.microservico.loja.DTO.VoucherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "transportador")
public interface TransportadorClient {

    @PostMapping(value = "/entrega")
    VoucherDTO reservaEntrega(InfoEntregaDTO entregaDto);
}
