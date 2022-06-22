package br.com.felipe.microservico.loja.client;

import br.com.felipe.microservico.loja.DTO.InfoPedidoDTO;
import br.com.felipe.microservico.loja.dtos.InfoFornecedorDTO;
import br.com.felipe.microservico.loja.dtos.ItemDaCompraDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FallBackFornecedorClient implements FornecedorClient {

    private final Logger log = LoggerFactory.getLogger(FornecedorClient.class);

    @Override
    public InfoFornecedorDTO getInfoPorEstado(String estado) {
        log.error("FallBackMethod - Get Info por Estado");
//        return new InfoFornecedorDTO();
        throw new NoFallbackAvailableException("Boom!", new RuntimeException());
    }

    @Override
    public InfoPedidoDTO realizaPedido(List<ItemDaCompraDTO> itens) {
        log.error("FallBackMethod - Realiza Pedido");
//        return new InfoPedidoDTO();
        throw new RuntimeException("Boom!", new RuntimeException());
    }
}
