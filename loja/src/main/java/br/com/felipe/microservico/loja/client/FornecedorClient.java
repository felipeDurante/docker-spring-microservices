package br.com.felipe.microservico.loja.client;

import br.com.felipe.microservico.loja.DTO.InfoPedidoDTO;
import br.com.felipe.microservico.loja.dtos.InfoFornecedorDTO;
import br.com.felipe.microservico.loja.dtos.ItemDaCompraDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "fornecedor", fallback = FallBackFornecedorClient.class)
public interface FornecedorClient {

    @GetMapping("/info/{estado}")
    InfoFornecedorDTO getInfoPorEstado(@PathVariable String estado);

    @PostMapping(value = "/pedido")
    InfoPedidoDTO realizaPedido(List<ItemDaCompraDTO> itens);

}
