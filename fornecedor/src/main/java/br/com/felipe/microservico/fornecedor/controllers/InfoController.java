package br.com.felipe.microservico.fornecedor.controllers;

import br.com.felipe.microservico.fornecedor.models.InfoFornecedor;
import br.com.felipe.microservico.fornecedor.responses.ResponseApiFornecedor;
import br.com.felipe.microservico.fornecedor.services.InfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

@RestController
@RequestMapping("/info")
public class InfoController {

    final Logger LOG = LoggerFactory.getLogger(InfoController.class);

    @Value("${server.port}")
    int aPort;

    @Autowired
    private InfoService infoService;

    @GetMapping(value = "/cadastra")
    public ResponseEntity<?> cadastraInformacoesIniciais() {

        return ResponseEntity.ok().body(infoService.cadastroInicial());

    }

    @GetMapping(value = "/{estado}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getInfoPorEstado(@PathVariable String estado) {

//        LOG.info("recebido pedido de informações do fornecedor de {}", estado);

        try {
            LOG.info("Porta: {}", aPort);
            LOG.info("HostAdress: {}", InetAddress.getLocalHost().getHostAddress());
            LOG.info("HostName: {}", InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Optional<InfoFornecedor> optionalInfoFornecedor = infoService.getInfoPorEstado(estado);

        if (optionalInfoFornecedor.isPresent()) {
            return ResponseEntity.ok().body(optionalInfoFornecedor.get());
        }

        return ResponseEntity.status(404).body(new ResponseApiFornecedor("fornecedor não encontrado", 204));


    }
}
