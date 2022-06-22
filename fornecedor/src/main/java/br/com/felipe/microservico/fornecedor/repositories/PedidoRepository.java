package br.com.felipe.microservico.fornecedor.repositories;

import br.com.felipe.microservico.fornecedor.models.Pedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {
}
