package br.com.mystore.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import br.com.mystore.api.exceptionhandler.Problema;
import br.com.mystore.api.v1.model.ClienteModel;
import br.com.mystore.api.v1.model.input.ClienteInput;
import br.com.mystore.api.v1.model.input.ClienteInputEnderecoIdInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Clientes")
public interface EmpresaClienteControllerOpenApi {

	@ApiOperation("Cadastra um cliente de um empresa")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cliente cadastrado"),
		@ApiResponse(code = 404, message = "Empresa não encontrado", response = Problema.class)
	})
	ClienteModel adicionar(
			@ApiParam(value = "ID do empresa", example = "1", required = true)
			Long empresaId,
			
			@ApiParam(name = "corpo", value = "Representação de um novo cliente", required = true)
			ClienteInput clienteInput);

	@ApiOperation("Atualiza um cliente de um empresa")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cliente atualizado"),
		@ApiResponse(code = 404, message = "Cliente de empresa não encontrado", response = Problema.class)
	})
	ClienteModel atualizar(
			@ApiParam(value = "ID do empresa", example = "1", required = true)
			Long empresaId,
			
			@ApiParam(value = "ID do cliente", example = "1", required = true)
			Long clienteId,
			
			@ApiParam(name = "corpo", value = "Representação de um cliente com os novos dados", 
				required = true)
			ClienteInputEnderecoIdInput clienteInputEnderecoIdInput);

	@ApiOperation("Busca um cliente de um empresa")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do empresa ou cliente inválido", response = Problema.class),
		@ApiResponse(code = 404, message = "Cliente de empresa não encontrado", response = Problema.class)
	})
	ClienteModel buscar(
			@ApiParam(value = "ID do empresa", example = "1", required = true)
			Long empresaId,
			
			@ApiParam(value = "ID do cliente", example = "1", required = true)
			Long clienteId);

	@ApiOperation("Lista os clientes de um empresa")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do empresa inválido", response = Problema.class),
		@ApiResponse(code = 404, message = "Empresa não encontrado", response = Problema.class)
	})
	CollectionModel<ClienteModel> listar(
			@ApiParam(value = "ID do empresa", example = "1", required = true)
			Long empresaId,
			
			@ApiParam(value = "Indica se deve ou não incluir clientes inativos no resultado da listagem", 
				example = "false", defaultValue = "false")
			Boolean incluirInativos);
	

	@ApiOperation("Lista os clientes atualizados de um empresa")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do empresa inválido", response = Problema.class),
		@ApiResponse(code = 404, message = "Empresa não encontrado", response = Problema.class)
	})
	CollectionModel<ClienteModel> listarAtualizados(
			@ApiParam(value = "ID do empresa", example = "1", required = true)
			Long empresaId,
			@ApiParam(value = "Número da última versão", example = "1", required = true)
			Long ultimaVersao);

}