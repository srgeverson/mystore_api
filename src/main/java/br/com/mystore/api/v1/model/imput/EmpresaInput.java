package br.com.mystore.api.v1.model.imput;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmpresaInput {

	@ApiModelProperty(example = "MyStore", required = true)
	@NotBlank
	private String nome;
	
	@Valid
	@NotNull
	private EnderecoInput endereco;
	
}
