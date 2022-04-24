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

	@Query("from clientes c where c.ativo = true and c.empresa = :empresa")
	List<Cliente> findAtivosByEmpresa(Empresa empresa);

	@Query("from clientes AS c WHERE c.empresa = :empresa AND c.versao > :versao")
	List<Cliente> findByMaiorVersao(Empresa empresa, Long versao);
	
	@Query("SELECT MAX(c.versao) FROM clientes AS c where c.empresa = :empresa")
	Long findByUltimaVersao(Empresa empresa);
}
