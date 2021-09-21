package br.com.mystore.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import br.com.mystore.domain.model.EmpresaModel;

public interface EmpresaRepositoryQueries {

	List<EmpresaModel> find(String nome, 
			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
	
	List<EmpresaModel> findComFreteGratis(String nome);

}