package br.com.mystore.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mystore.domain.exception.ProdutoNaoEncontradoException;
import br.com.mystore.domain.model.Produto;
import br.com.mystore.domain.repository.ProdutoRepository;

@Service
public class CadastroProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Produto buscarOuFalhar(Long empresaId, Long produtoId) {
		return produtoRepository.findById(produtoId)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(empresaId, produtoId));
	}

	@Transactional
	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}
}
