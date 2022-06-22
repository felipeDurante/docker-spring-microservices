package br.com.felipe.microservico.fornecedor.repositories;

import br.com.felipe.microservico.fornecedor.models.InfoFornecedor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InfoRepository extends CrudRepository<InfoFornecedor, Long> {

    Optional<InfoFornecedor> findByEstado(String estado);

}
