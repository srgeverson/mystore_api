package br.com.paulistense.mystore.domain.exception;

public class EntidadeNaoEncontradaException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public EntidadeNaoEncontradaException(Long entidadeId) {
		super(String.format("Não existe um registro com código %d", entidadeId));
	}

	public EntidadeNaoEncontradaException(String entidade, Long entidadeId) {
		super(String.format("Não existe um(a) " + entidade + " cadastrado(a) com código %d", entidadeId));
	}

}