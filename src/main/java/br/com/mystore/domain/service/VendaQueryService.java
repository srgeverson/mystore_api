package br.com.mystore.domain.service;

import java.util.List;

import br.com.mystore.domain.filter.VendaDiariaFilter;
import br.com.mystore.domain.model.dto.VendaDiaria;

public interface VendaQueryService {

	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
	
}
