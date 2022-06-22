package br.com.felipe.microservico.fornecedor.services;

import br.com.felipe.microservico.fornecedor.models.InfoFornecedor;
import br.com.felipe.microservico.fornecedor.repositories.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InfoService {

    @Autowired
    private InfoRepository infoRepository;

    public Optional<InfoFornecedor> getInfoPorEstado(String estado) {

        return infoRepository.findByEstado(estado);

    }

    public List<InfoFornecedor> cadastroInicial() {

        List<InfoFornecedor> infoFornecedors = new ArrayList<>();

        InfoFornecedor info = new InfoFornecedor("SP","Rua do caralho", "Fornecedor do Caralho");
        infoFornecedors.add(infoRepository.save(info));
        info = new InfoFornecedor("MS","Rua do kacete", "Fornecedor do MS");
        infoFornecedors.add(infoRepository.save(info));
        info = new InfoFornecedor("SC","Rua do SC", "Fornecedor do SC");
        infoFornecedors.add(infoRepository.save(info));
        info = new InfoFornecedor("RR","Rua da pqp", "Fornecedor do RR");
        infoFornecedors.add(infoRepository.save(info));

        return infoFornecedors;


    }
}
