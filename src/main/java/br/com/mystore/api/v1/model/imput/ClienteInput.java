package br.com.mystore.api.v1.model.imput;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClienteInput {

	@ApiModelProperty(example = "MyStore PDV", required = true)
	@NotBlank
	private String apelidoNomeFantazia;

	@ApiModelProperty(example = "MyStore LTDA", required = false)
	private String nomeRazaoSocial;

	@ApiModelProperty(example = "00.000.000/0000-00", required = false)
	private String cpfCnpj;
	
	@ApiModelProperty(example = "mystore@gmail.com", required = false)
	@Email
	private String email;

	@ApiModelProperty(example = "(00) 0000-0000", required = false)
	private String telefone;
	
	@ApiModelProperty(example = "(00) 00000-0000", required = false)
	private String celular;

	@Valid
	private EnderecoInput endereco;
	
}
