package br.com.paulistense.mystore.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.paulistense.mystore.api.v1.model.input.GraficoInput;
import br.com.paulistense.mystore.domain.model.Grafico;

@Component
public class GraficoInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public void copyToDomainObject(GraficoInput aplicacaoInput, Grafico aplicacao) {
		modelMapper.map(aplicacaoInput, aplicacao);
	}

	public Grafico toDomainObject(GraficoInput aplicacaoInput) {
		return modelMapper.map(aplicacaoInput, Grafico.class);
	}


}
