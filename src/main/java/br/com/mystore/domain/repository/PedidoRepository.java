package br.com.mystore.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.mystore.domain.model.Pedido;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long>,
		JpaSpecificationExecutor<Pedido> {

	Optional<Pedido> findByCodigo(String codigo);

	@Query("from pedidos p join fetch p.cliente join fetch p.empresa e")
	List<Pedido> findAll();
	
	boolean isPedidoGerenciadoPor(String codigoPedido);
	
}
