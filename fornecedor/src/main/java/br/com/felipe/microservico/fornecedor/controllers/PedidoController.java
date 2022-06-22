package br.com.felipe.microservico.fornecedor.controllers;

import br.com.felipe.microservico.fornecedor.dtos.ItemDoPedidoDTO;
import br.com.felipe.microservico.fornecedor.models.Pedido;
import br.com.felipe.microservico.fornecedor.services.PedidoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    final Logger LOG = LoggerFactory.getLogger(PedidoController.class);

    @Autowired
    private PedidoService pedidoService;

    @Value("${server.port}")
    int aPort;

    @PostMapping
    public Pedido realizaPedido(@RequestBody List<ItemDoPedidoDTO> produtos) {
        LOG.info("Pedido da loja recebido.... {}", produtos);
        try {
            LOG.info("Porta: {}", aPort);
            LOG.info("HostAdress: {}", InetAddress.getLocalHost().getHostAddress());
            LOG.info("HostName: {}", InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return pedidoService.realizaPedido(produtos);
    }

    @GetMapping("/{id}")
    public Pedido getPedidoPorId(@PathVariable Long id) {
        return pedidoService.getPedidoPorId(id);
    }
}
