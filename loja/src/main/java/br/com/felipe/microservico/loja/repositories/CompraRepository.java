package br.com.felipe.microservico.loja.repositories;

import br.com.felipe.microservico.loja.models.Compra;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends CrudRepository<Compra, Long> {
}
