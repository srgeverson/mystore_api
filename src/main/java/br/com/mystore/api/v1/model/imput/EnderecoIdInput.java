package br.com.mystore.api.v1.model.imput;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoIdInput {
	
	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Long id;
	
	@ApiModelProperty(example = "60000-000", required = true)
	@NotBlank
	private String cep;
	
	@ApiModelProperty(example = "Rua Floriano Peixoto", required = true)
	@NotBlank
	private String logradouro;
	
	@ApiModelProperty(example = "\"1500\"", required = true)
	@NotBlank
	private String numero;
	
	@ApiModelProperty(example = "Apto 901")
	private String complemento;
	
	@ApiModelProperty(example = "Centro", required = true)
	@NotBlank
	private String bairro;
	
	@Valid
	@NotNull
	private CidadeIdInput cidade;
	
}
