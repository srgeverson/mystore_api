package br.com.mystore.api.v1.model.imput;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FormaPagamentoInput {

	@ApiModelProperty(example = "Cartão de crédito", required = true)
	@NotBlank
	private String descricao;
	
}
