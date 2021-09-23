package br.com.mystore.domain.repository;

import org.springframework.stereotype.Repository;

import br.com.mystore.domain.model.Empresa;

@Repository
public interface EmpresaRepository extends CustomJpaRepository<Empresa, Long> {

}
