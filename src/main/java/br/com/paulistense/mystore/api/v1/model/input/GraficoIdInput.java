package br.com.paulistense.mystore.api.v1.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GraficoIdInput {

	@NotNull
	private Long id;

}
