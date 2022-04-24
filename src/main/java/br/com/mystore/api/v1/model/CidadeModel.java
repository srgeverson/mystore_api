package br.com.mystore.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cidades")
@Setter
@Getter
public class CidadeModel extends RepresentationModel<CidadeModel> {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Fortaleza")
	private String nome;
	
	@ApiModelProperty(example = "true")
	private Boolean ativo;
	
	@ApiModelProperty(example = "1")
	private Long versao;
	
	private EstadoModel estado;
	
}
