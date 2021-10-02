package br.com.mystore.api.v1.openapi.controller;

import org.springframework.beans.factory.parsing.Problem;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import br.com.mystore.api.v1.model.UsuarioModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Empresas")
public interface EmpresaUsuarioResponsavelControllerOpenApi {

	@ApiOperation("Lista os usuários responsáveis associados a empresa")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Empresa não encontrado", response = Problem.class)
	})
	CollectionModel<UsuarioModel> listar(
			@ApiParam(value = "ID do empresa", example = "1", required = true)
			Long empresaId);

	@ApiOperation("Desassociação de empresa com usuário responsável")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
		@ApiResponse(code = 404, message = "Empresa ou usuário não encontrado", 
			response = Problem.class)
	})
	ResponseEntity<Void> desassociar(
			@ApiParam(value = "ID do empresa", example = "1", required = true)
			Long empresaId,
			
			@ApiParam(value = "ID do usuário", example = "1", required = true)
			Long usuarioId);

	@ApiOperation("Associação de empresa com usuário responsável")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Associação realizada com sucesso"),
		@ApiResponse(code = 404, message = "Empresa ou usuário não encontrado", 
			response = Problem.class)
	})
	ResponseEntity<Void> associar(
			@ApiParam(value = "ID do empresa", example = "1", required = true)
			Long empresaId,
			
			@ApiParam(value = "ID do usuário", example = "1", required = true)
			Long usuarioId);

}