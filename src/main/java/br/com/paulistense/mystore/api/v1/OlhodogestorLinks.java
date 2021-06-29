package br.com.paulistense.mystore.api.v1;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import br.com.paulistense.mystore.api.v1.controller.GraficoController;
import br.com.paulistense.mystore.api.v1.controller.SpcController;

@Component
public class OlhodogestorLinks {

	public Link linkToGrafico(Long graficoId, String rel) {
		return linkTo(methodOn(GraficoController.class).buscar(graficoId)).withRel(rel);
	}

	public Link linkToGraficos(String rel) {
		return linkTo(GraficoController.class).withRel(rel);
	}

	public Link linkToGraficos() {
		return linkToGraficos(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToSpcs(String rel) {
		return linkTo(SpcController.class).withRel(rel);
	}

	public Link linkToSpcs() {
		return linkToGraficos(IanaLinkRelations.SELF.value());
	}	
}
