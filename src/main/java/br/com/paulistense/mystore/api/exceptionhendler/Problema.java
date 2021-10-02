package br.com.paulistense.mystore.api.exceptionhendler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problema {
	private Integer status;
	private LocalDateTime dataHora;
	private String tipo;
	private String titulo;
	private String detalhe;
	private String menssagemUsuario;
	private List<Campo> campos;

	@Getter
	@Builder
	public static class Campo {

		private String nome;
		private String menssagemUsuario;

	}
}
