package br.com.mystore.api.v1.model;

import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "empresas")
@Setter
@Getter
public class EmpresaModel extends RepresentationModel<EmpresaModel> {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "MyStore LTDA")
	private String nome;
	
	@ApiModelProperty(example = "00.000.000/0000-00")
	private String cpfCnpj;
	
	private OffsetDateTime dataCadastro;
	
	private OffsetDateTime dataAtualizacao;
	
	private Boolean ativo;
	
	private EnderecoModel endereco;
	
}
