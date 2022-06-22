package br.com.felipe.microservico.fornecedor.services;

import br.com.felipe.microservico.fornecedor.models.Produto;
import br.com.felipe.microservico.fornecedor.repositories.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final Logger log = LoggerFactory.getLogger(ProdutoService.class);

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> getProdutosPorEstado(String estado) {
        log.info("Get produtos por estados ... Procurando por: {}", estado);
        return produtoRepository.findByEstado(estado);
    }
}
