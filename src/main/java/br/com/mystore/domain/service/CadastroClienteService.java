package br.com.mystore.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mystore.domain.exception.ProdutoNaoEncontradoException;
import br.com.mystore.domain.model.Cliente;
import br.com.mystore.domain.model.Endereco;
import br.com.mystore.domain.repository.ClienteRepository;

@Service
public class CadastroClienteService {

	@Autowired
	private CadastroEnderecoService cadastroEndereco;

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente buscarOuFalhar(Long empresaId, Long clienteId) {
		return clienteRepository.findById(clienteId)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(empresaId, clienteId));
	}

	@Transactional
	public Cliente salvar(Cliente cliente) {
		Endereco endereco = cadastroEndereco.salvar(cliente.getEndereco());

		cliente.setEndereco(endereco);

		cliente.setAtivo(true);
		
		return clienteRepository.save(cliente);
	}

}
