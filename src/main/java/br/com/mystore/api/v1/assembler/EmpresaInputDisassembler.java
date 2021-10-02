package br.com.mystore.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mystore.api.v1.model.imput.EmpresaInput;
import br.com.mystore.domain.model.Cidade;
import br.com.mystore.domain.model.Empresa;
import br.com.mystore.domain.model.Endereco;

@Component
public class EmpresaInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Empresa toDomainObject(EmpresaInput empresaInput) {
		return modelMapper.map(empresaInput, Empresa.class);
	}

	public void copyToDomainObject(EmpresaInput empresaInput, Empresa empresa) {
		empresa.setEndereco(new Endereco());
		empresa.getEndereco().setCidade(new Cidade());

		modelMapper.map(empresaInput, empresa);
	}

}
