package br.com.mystore.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.mystore.domain.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	@Query("from cidades cid where cid.ativo = :ativo")
	List<Cidade> findByAtivo(Boolean ativo);
	
}
