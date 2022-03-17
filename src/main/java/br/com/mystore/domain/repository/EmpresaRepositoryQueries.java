package br.com.mystore.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import br.com.mystore.domain.model.Empresa;

public interface EmpresaRepositoryQueries {

	List<Empresa> find(String nome, 
			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
	
	List<Empresa> findComFreteGratis(String nome);

}