package br.com.mystore.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import br.com.mystore.api.v1.model.EmpresaBasicoModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("EmpresasBasicoModel")
@Data
public class EmpresasBasicoModelOpenApi {

	private RestaurantesEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@ApiModel("RestaurantesEmbeddedModel")
	@Data
	public class RestaurantesEmbeddedModelOpenApi {
		
		private List<EmpresaBasicoModel> restaurantes;
		
	}
	
}
