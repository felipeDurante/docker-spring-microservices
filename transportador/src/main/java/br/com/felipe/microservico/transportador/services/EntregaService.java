package br.com.felipe.microservico.transportador.services;

import br.com.felipe.microservico.transportador.dtos.EntregaDTO;
import br.com.felipe.microservico.transportador.dtos.VoucherDTO;
import br.com.felipe.microservico.transportador.models.Entrega;
import br.com.felipe.microservico.transportador.repositories.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntregaService {

    @Autowired
    private EntregaRepository repository;

    public VoucherDTO reservaEntrega(EntregaDTO pedidoDTO) {

        Entrega entrega = new Entrega();
        entrega.setDataParaBusca(pedidoDTO.getDataParaEntrega());
        entrega.setPrevisaoParaEntrega(pedidoDTO.getDataParaEntrega().plusDays(1l));
        entrega.setEnderecoDestino(pedidoDTO.getEnderecoDestino());
        entrega.setEnderecoOrigem(pedidoDTO.getEnderecoOrigem());
        entrega.setPedidoId(pedidoDTO.getPedidoId());

        repository.save(entrega);

        VoucherDTO voucher = new VoucherDTO();
        voucher.setNumero(entrega.getId());
        voucher.setPrevisaoParaEntrega(entrega.getPrevisaoParaEntrega());
        return voucher;
    }
}
