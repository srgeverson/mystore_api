package br.com.mystore.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mystore.domain.exception.EnderecoNaoEncontradoException;
import br.com.mystore.domain.exception.EntidadeEmUsoException;
import br.com.mystore.domain.model.Cidade;
import br.com.mystore.domain.model.Endereco;
import br.com.mystore.domain.repository.EnderecoRepository;

@Service
public class CadastroEnderecoService {

	private static final String MSG_ESTADO_EM_USO = "Endereço de código %d não pode ser removido, pois está em uso";

	@Autowired
	private CadastroCidadeService cadastroCidade;

	@Autowired
	private EnderecoRepository endedeEnderecoRepository;

	@Transactional
	public Endereco salvar(Endereco endereco) {
		Long cidadeId = endereco.getCidade().getId();
		Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);
		endereco.setCidade(cidade);
		return endedeEnderecoRepository.save(endereco);
	}

	@Transactional
	public void excluir(Long enderecoId) {
		try {
			endedeEnderecoRepository.deleteById(enderecoId);
			endedeEnderecoRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new EnderecoNaoEncontradoException(enderecoId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO, enderecoId));
		}
	}

	public Endereco buscarOuFalhar(Long enderecoId) {
		return endedeEnderecoRepository.findById(enderecoId)
				.orElseThrow(() -> new EnderecoNaoEncontradoException(enderecoId));
	}

}
