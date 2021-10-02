package br.com.mystore.infrastructure.repository.spec;

import org.springframework.data.jpa.domain.Specification;

import br.com.mystore.domain.model.Empresa;

public class RestauranteSpecs {
	
	public static Specification<Empresa> comNomeSemelhante(String nome) {
		return (root, query, builder) ->
			builder.like(root.get("nome"), "%" + nome + "%");
	}
	
}
