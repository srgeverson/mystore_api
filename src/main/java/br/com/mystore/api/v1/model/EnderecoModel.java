package br.com.mystore.api.v1.model;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoModel {

	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Long id;

	@ApiModelProperty(example = "38.400-000")
	private String cep;

	@ApiModelProperty(example = "Rua Floriano Peixoto")
	private String logradouro;

	@ApiModelProperty(example = "\"1500\"")
	private String numero;

	@ApiModelProperty(example = "Apto 901")
	private String complemento;

	@ApiModelProperty(example = "Centro")
	private String bairro;

	private CidadeResumoModel cidade;

}
