package br.com.paulistense.mystore.domain.exception;

public class EntidadeEmUsoException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoException(String mensagem) {
		super(mensagem);
	}

	public EntidadeEmUsoException(String entidade, Long entidadeId) {
		super(String.format("A entidade " + entidade + " com código %d está em uso", entidadeId));
	}

}