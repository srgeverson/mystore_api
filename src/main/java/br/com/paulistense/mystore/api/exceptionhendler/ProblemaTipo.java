package br.com.paulistense.mystore.api.exceptionhendler;

import lombok.Getter;

@Getter
public enum ProblemaTipo {
	DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"), 
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
	ERRO_NEGOCIO("/erro-negocio", "Violação da regra de negócio"),
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado");

	private String titulo;
	private String uri;

	ProblemaTipo(String path, String titulo) {
		this.uri = "https://www.ivitech.com.br" + path;
		this.titulo = titulo;
	}
}
