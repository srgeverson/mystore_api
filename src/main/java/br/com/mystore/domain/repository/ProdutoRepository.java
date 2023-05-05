package br.com.mystore.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.mystore.domain.model.Empresa;
import br.com.mystore.domain.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query("from produtos where empresa.id = :empresa and id = :produto")
	Optional<Produto> findById(@Param("empresa") Long empresaId, @Param("produto") Long produtoId);

	List<Produto> findTodosByEmpresa(Empresa empresa);

	@Query("from produtos p where p.ativo = true and p.empresa = :empresa")
	List<Produto> findAtivosByEmpresa(Empresa empresa);
}
