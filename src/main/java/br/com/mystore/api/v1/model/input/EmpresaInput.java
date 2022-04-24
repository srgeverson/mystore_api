package br.com.mystore.api.v1.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmpresaInput {

	@ApiModelProperty(example = "MyStore LTDA", required = true)
	@NotBlank
	private String nome;

	@ApiModelProperty(example = "000.000.000-00/00.000.000/0000-00", required = true)
	@NotBlank
	private String cpfCnpj;

	@ApiModelProperty(example = "(00) 00000-0000", required = true)
	@NotBlank
	private String telefone;

	@Valid
	@NotNull
	private EnderecoInput endereco;

}
