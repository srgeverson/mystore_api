package br.com.mystore.api.v1.model.imput;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioComSenhaInput {

	@ApiModelProperty(example = "joao.ger@mystore.com.br", required = true)
	@NotBlank
	@Email
	private String email;
	
	@ApiModelProperty(example = "123456", required = true)
	@NotBlank
	private String senha;
	
	@ApiModelProperty(example = "12345678", required = true)
	@PositiveOrZero
	private Integer codigoAcesso;
	
}
