package br.com.paulistense.mystore.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import br.com.paulistense.mystore.api.v1.model.SpcModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "SPC")
public interface SpcControllerOpenApi {

	@ApiOperation(value	= "Análise da carteira de cliente", response = SpcControllerOpenApi.class)	
	CollectionModel<SpcModel> carteiraDeClientes();
}
