package br.com.mystore.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.mystore.domain.model.Empresa;

@Repository
public interface EmpresaRepository extends CustomJpaRepository<Empresa, Long> {

	@Query("from empresas where nome like %:nome%")
	List<Empresa> findByNome(String nome);
}
