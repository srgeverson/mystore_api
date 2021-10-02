package br.com.paulistense.mystore.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.paulistense.mystore.domain.exception.EntidadeNaoEncontradaException;
import br.com.paulistense.mystore.domain.model.Grafico;
import br.com.paulistense.mystore.domain.repository.GraficoRepository;

@Service
public class GraficoService {

	@Autowired
	private GraficoRepository aplicacaoRepository;

	public Grafico buscarOuFalhar(Long aplicacaoId) {
		return aplicacaoRepository.findById(aplicacaoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Gráfico não encontrado"));
	}

}
