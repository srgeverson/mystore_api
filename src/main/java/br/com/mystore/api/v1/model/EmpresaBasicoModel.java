package br.com.mystore.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "empresas")
@Setter
@Getter
public class EmpresaBasicoModel extends RepresentationModel<EmpresaBasicoModel> {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "MyStore")
	private String nome;

}
