package br.com.mystore.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.mystore.domain.model.EmpresaModel;

@Repository
public interface EmpresaRepository 
		extends CustomJpaRepository<EmpresaModel, Long>, EmpresaRepositoryQueries,
		JpaSpecificationExecutor<EmpresaModel> {

	@Query("from Restaurante r join fetch r.cozinha")
	List<EmpresaModel> findAll();
	
	List<EmpresaModel> queryByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
//	@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
	List<EmpresaModel> consultarPorNome(String nome, @Param("id") Long cozinha);
	
//	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);
	
	Optional<EmpresaModel> findFirstRestauranteByNomeContaining(String nome);
	
	List<EmpresaModel> findTop2ByNomeContaining(String nome);
	
	int countByCozinhaId(Long cozinha);
	
	boolean existsResponsavel(Long restauranteId, Long usuarioId);
	
}
