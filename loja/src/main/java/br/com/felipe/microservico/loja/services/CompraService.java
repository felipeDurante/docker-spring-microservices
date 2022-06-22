package br.com.felipe.microservico.loja.services;

import br.com.felipe.microservico.loja.DTO.InfoEntregaDTO;
import br.com.felipe.microservico.loja.DTO.InfoPedidoDTO;
import br.com.felipe.microservico.loja.DTO.VoucherDTO;
import br.com.felipe.microservico.loja.client.FornecedorClient;
import br.com.felipe.microservico.loja.client.TransportadorClient;
import br.com.felipe.microservico.loja.dtos.CompraDTO;
import br.com.felipe.microservico.loja.dtos.InfoFornecedorDTO;
import br.com.felipe.microservico.loja.models.Compra;
import br.com.felipe.microservico.loja.models.CompraState;
import br.com.felipe.microservico.loja.repositories.CompraRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Map;
import java.util.function.Supplier;

@Service
public class CompraService {

    final Logger LOG = LoggerFactory.getLogger(CompraService.class);

    @Autowired
    private FornecedorClient fornecedorClient;

    @Autowired
    private TransportadorClient transportadorClient;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate rest;

    @Autowired
    @Qualifier("restTemplateWithLoadBalance")
    private RestTemplate restLoadBalance;

    public Supplier<Map> delaySuppplier(int seconds) {
        return () -> this.delay(seconds);
    }

    public Map delay(int seconds) {

        LOG.info("delay method");
        return rest.getForObject("https://httpbin.org/delay/" + seconds, Map.class);
    }

    public Supplier<Compra> realizaCompraCircuirt(CompraDTO compra) {

        final String estado = compra.getEndereco().getEstado();

        LOG.info("buscando informações do fornecedor de {} ", estado);
        InfoFornecedorDTO info = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());

        LOG.info("realizando um pedido");

        InfoPedidoDTO infoPedidoDTO = fornecedorClient.realizaPedido(compra.getItens());


        Compra compra1 = new Compra(infoPedidoDTO.getId(), infoPedidoDTO.getTempoDePreparo(), (compra.getEndereco().toString()));


        return () -> compra1;
    }

    public Compra realizaCompra(CompraDTO compra) {

        Compra compraSalva = new Compra();
        compraSalva.setState(CompraState.RECEBIDO);
        compraRepository.save(compraSalva);

        InfoFornecedorDTO info = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());
        InfoPedidoDTO pedido = fornecedorClient.realizaPedido(compra.getItens());

        compraSalva.setState(CompraState.PEDIDO_REALIZADO);
        compraSalva.setPedidoId(pedido.getId());
        compraSalva.setTempoDePreparo(pedido.getTempoDePreparo());
        compraSalva.setEnderecoDestino(compra.getEndereco().toString());
        compraRepository.save(compraSalva);

        InfoEntregaDTO entregaDto = new InfoEntregaDTO();
        entregaDto.setPedidoId(pedido.getId());
        entregaDto.setDataParaEntrega(LocalDate.now().plusDays(pedido.getTempoDePreparo()));
        entregaDto.setEnderecoOrigem(info.getEndereco());
        entregaDto.setEnderecoDestino(compra.getEndereco().toString());

        VoucherDTO voucher = transportadorClient.reservaEntrega(entregaDto);
        compraSalva.setState(CompraState.RESERVA_ENTREGA_REALIZADA);
        compraSalva.setDataParaEntrega(voucher.getPrevisaoParaEntrega());
        compraSalva.setVoucher(voucher.getNumero());
        compraRepository.save(compraSalva);

//        LOG.info("Pedido retornado... \n " + pedido);
//
//        compraSalva = new Compra(
//                pedido.getId(),
//                pedido.getTempoDePreparo(),
//                compra.getEndereco().toString(),
//                voucher.getPrevisaoParaEntrega(),
//                voucher.getNumero());

        LOG.info(compraSalva.toString());
//        compraSalva.setPedidoId(pedido.getId());
//        compraSalva.setTempoDePreparo(pedido.getTempoDePreparo());
//        compraSalva.setEnderecoDestino(compra.getEndereco().toString());
//        compraSalva.setDataParaEntrega(voucher.getPrevisaoParaEntrega());
//        compraSalva.setVoucher(voucher.getNumero());
        compraRepository.save(compraSalva);

        return compraSalva;
    }

}
