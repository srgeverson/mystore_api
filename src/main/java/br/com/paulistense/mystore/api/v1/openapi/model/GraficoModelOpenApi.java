package br.com.paulistense.mystore.api.v1.openapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("AplicacaoModelBasico")
@Setter
@Getter
public class GraficoModelOpenApi {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "IVICOM")
	private String nome;

}

