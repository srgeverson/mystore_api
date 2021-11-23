package br.com.mystore.api.v1.openapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Configuração")
public interface HostControllerOpenApi {
	
	@ApiOperation("Configuração de rede")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Informação  de rede"),
	})
	String checkHost();
}
