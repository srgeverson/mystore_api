package br.com.mystore.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import br.com.mystore.api.exceptionhandler.Problema;
import br.com.mystore.api.v1.model.CidadeModel;
import br.com.mystore.api.v1.model.input.CidadeInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {
	
	@ApiOperation("Cadastra uma cidade")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cidade cadastrada"),
	})
	CidadeModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma nova cidade", required = true)
			CidadeInput cidadeInput);
	
	@ApiOperation("Atualiza uma cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cidade atualizada"),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problema.class)
	})
	CidadeModel atualizar(
			@ApiParam(value = "ID de uma cidade", example = "1", required = true) 
			Long cidadeId,
			
			@ApiParam(name = "corpo", value = "Representação de uma cidade com os novos dados", required = true)
			CidadeInput cidadeInput);
	
	@ApiOperation("Busca uma cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da cidade inválido", response = Problema.class),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problema.class)
	})
	CidadeModel buscar(
			@ApiParam(value = "ID de uma cidade", example = "1", required = true)
			Long cidadeId);

	@ApiOperation("Lista Todas Cidades")
	CollectionModel<CidadeModel> listar();
	
	@ApiOperation("Lista as cidades que foram atualizadas")
	CollectionModel<CidadeModel> listarAtualizadas(
			@ApiParam(value = "Número da última versão", example = "1", required = true)
			Long versao);
	
	@ApiOperation("Exclui uma cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cidade excluída"),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problema.class)
	})
	void remover(
			@ApiParam(value = "ID de uma cidade", example = "1", required = true)
			Long cidadeId);

}
