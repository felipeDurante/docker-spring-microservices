package br.com.felipe.microservico.loja.services;

import br.com.felipe.microservico.loja.dtos.CompraDTO;
import br.com.felipe.microservico.loja.dtos.InfoFornecedorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CompraServiceComEurekaClientERestTemplate {

    private final String fornecedorApi = "fornecedor";

    @Autowired
    @Qualifier("restTemplateWithLoadBalance")
    private RestTemplate client;

    @Autowired
    private DiscoveryClient eurekaClient;

    public void realizaCompra(CompraDTO compra) {

        ResponseEntity<InfoFornecedorDTO> info = client.exchange("http://" + fornecedorApi + "/info/" + compra.getEndereco().getEstado(),
                HttpMethod.GET,
                null,
                InfoFornecedorDTO.class);

        InfoFornecedorDTO response = info.getBody();

    }
}
