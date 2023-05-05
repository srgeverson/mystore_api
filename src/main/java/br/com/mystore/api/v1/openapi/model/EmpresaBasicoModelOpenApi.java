package br.com.mystore.api.v1.openapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("EmpresaBasicoModel")
@Setter
@Getter
public class EmpresaBasicoModelOpenApi {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "MyStore LTDA")
	private String nome;
}
