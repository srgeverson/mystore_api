package br.com.mystore.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.mystore.domain.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

	@Query("from estados e where e.ativo = :ativo")
	List<Estado> findByAtivo(Boolean ativo);
	
	@Query("from estados e where e.versao > :versao")
	List<Estado	> findByMaiorVersao(Long versao);
	
	@Query("SELECT MAX(e.versao) FROM estados AS e")
	Long findByUltimaVersao();
	
}
