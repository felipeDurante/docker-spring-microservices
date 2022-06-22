package br.com.felipe.microservico.transportador.repositories;

import br.com.felipe.microservico.transportador.models.Entrega;
import org.springframework.data.repository.CrudRepository;

public interface EntregaRepository extends CrudRepository<Entrega, Long> {

}