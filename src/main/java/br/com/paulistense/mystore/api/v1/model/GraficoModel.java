package br.com.paulistense.mystore.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "graficos")
@Setter
@Getter
public class GraficoModel extends RepresentationModel<GraficoModel> {

	private Long id;
	private String descricao;
	private String tipo;	
	private Integer largura;
	private Integer altura;

}

