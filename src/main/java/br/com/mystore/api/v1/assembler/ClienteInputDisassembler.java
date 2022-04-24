package br.com.mystore.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mystore.api.v1.model.input.ClienteInput;
import br.com.mystore.api.v1.model.input.ClienteInputEnderecoIdInput;
import br.com.mystore.domain.model.Cidade;
import br.com.mystore.domain.model.Cliente;
import br.com.mystore.domain.model.Endereco;

@Component
public class ClienteInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Cliente toDomainObject(ClienteInput clienteInput) {
		return modelMapper.map(clienteInput, Cliente.class);
	}

	public void copyToDomainObject(ClienteInput clienteInput, Cliente cliente) {
		cliente.setEndereco(new Endereco());
		cliente.getEndereco().setCidade(new Cidade());

		modelMapper.map(clienteInput, cliente);
	}

	public void copyToDomainObject(ClienteInputEnderecoIdInput clienteInputEnderecoIdInput, Cliente cliente) {
		cliente.setEndereco(new Endereco());
		cliente.getEndereco().setCidade(new Cidade());

		modelMapper.map(clienteInputEnderecoIdInput, cliente);
	}

}
