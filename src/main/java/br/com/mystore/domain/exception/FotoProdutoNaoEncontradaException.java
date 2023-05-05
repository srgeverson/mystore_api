package br.com.mystore.domain.exception;

public class FotoProdutoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public FotoProdutoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public FotoProdutoNaoEncontradaException(Long empresaId, Long produtoId) {
		this(String.format("Não existe um cadastro de foto do produto com código %d para o empresa de código %d",
				produtoId, empresaId));
	}

}