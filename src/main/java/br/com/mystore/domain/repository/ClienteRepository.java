package br.com.mystore.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.mystore.domain.model.Cliente;
import br.com.mystore.domain.model.Empresa;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Query("from clientes where empresa.id = :empresa and id = :cliente")
	Optional<Cliente> findById(@Param("empresa") Long empresaId, @Param("cliente") Long produtoId);

	List<Cliente> findTodosByEmpresa(Empresa empresa);

	@Query("from clientes p where p.ativo = true and p.empresa = :empresa")
	List<Cliente> findAtivosByEmpresa(Empresa empresa);

}
