package br.com.paulistense.mystore.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "pedidos-vendas")
@Setter
@Getter
public class SpcModel extends RepresentationModel<SpcModel> {

	private Long idCdemp;
	private Long idNumpc;

}

