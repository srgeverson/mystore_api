package br.com.mystore.api.v1.model;

import javax.validation.constraints.Email;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import br.com.mystore.api.v1.model.input.EnderecoInput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "clientes")
@Setter
@Getter
public class ClienteModel extends RepresentationModel<ClienteModel> {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "MyStore PDV")
	private String apelidoNomeFantazia;

	@ApiModelProperty(example = "MyStore LTDA")
	private String nomeRazaoSocial;

	@ApiModelProperty(example = "00.000.000/0000-00")
	private String cpfCnpj;
	
	@ApiModelProperty(example = "mystore@gmail.com")
	@Email
	private String email;

	@ApiModelProperty(example = "(00) 0000-0000")
	private String telefone;
	
	@ApiModelProperty(example = "(00) 00000-0000")
	private String celular;
	
	@ApiModelProperty(example = "true")
	private Boolean ativo;
	
	@ApiModelProperty(example = "1")
	private Long versao;

	private EnderecoInput endereco;
	
}
