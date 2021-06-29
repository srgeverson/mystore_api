package br.com.paulistense.mystore.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import br.com.paulistense.mystore.api.exceptionhendler.Problema;
import br.com.paulistense.mystore.api.v1.model.GraficoModel;
import br.com.paulistense.mystore.api.v1.openapi.model.GraficoModelOpenApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Gráficos")
public interface GraficoControllerOpenApi {

	@ApiOperation("Busca um gráfico por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do gráfico inválido", response = Problema.class),
		@ApiResponse(code = 404, message = "Gráfico não encontrado", response = Problema.class)
	})	
	GraficoModel buscar(
			@ApiParam(value = "ID de um gráfico", example = "1", required = true)
			Long aplicacaoId);


	@ApiOperation(value	= "Lista as gráficos", response = GraficoModelOpenApi.class)	
	CollectionModel<GraficoModel> listar();
}
