package br.com.mystore.api.v1.openapi.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import br.com.mystore.api.exceptionhandler.Problema;
import br.com.mystore.api.v1.model.EmpresaApenasNomeModel;
import br.com.mystore.api.v1.model.EmpresaBasicoModel;
import br.com.mystore.api.v1.model.EmpresaModel;
import br.com.mystore.api.v1.model.imput.EmpresaEnderecoIdInput;
import br.com.mystore.api.v1.model.imput.EmpresaInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Empresas")
public interface EmpresaControllerOpenApi {

	@ApiOperation("Cadastra um empresa")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Empresa cadastrado"),
	})
	EmpresaModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de um novo empresa", required = true)
			EmpresaInput empresaInput);
	
	@ApiOperation("Atualiza um empresa por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Empresa atualizado"),
		@ApiResponse(code = 404, message = "Empresa não encontrado", response = Problema.class)
	})
	EmpresaModel atualizar(
			@ApiParam(value = "ID de um empresa", example = "1", required = true)
			Long empresaId,
			
			@ApiParam(name = "corpo", value = "Representação de um empresa com os novos dados", 
				required = true)
			EmpresaEnderecoIdInput empresaEnderecoIdInput);
	
	@ApiOperation("Ativa um empresa por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Empresa ativado com sucesso"),
		@ApiResponse(code = 404, message = "Empresa não encontrado", response = Problema.class)
	})
	ResponseEntity<Void> ativar(
			@ApiParam(value = "ID de um empresa", example = "1", required = true)
			Long empresaId);
	
	@ApiOperation("Ativa múltiplos empresas")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Empresas ativados com sucesso")
	})
	void ativarMultiplos(
			@ApiParam(name = "corpo", value = "IDs de empresas", required = true)
			List<Long> empresaIds);
	
	@ApiOperation("Busca um empresa por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do empresa inválido", response = Problema.class),
		@ApiResponse(code = 404, message = "Empresa não encontrado", response = Problema.class)
	})
	EmpresaModel buscar(
			@ApiParam(value = "ID de um empresa", example = "1", required = true)
			Long empresaId);
	
	@ApiOperation("Inativa um empresa por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Empresa inativado com sucesso"),
		@ApiResponse(code = 404, message = "Empresa não encontrado", response = Problema.class)
	})
	ResponseEntity<Void> inativar(
			@ApiParam(value = "ID de um empresa", example = "1", required = true)
			Long empresaId);

	@ApiOperation("Inativa múltiplos empresas")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Empresas ativados com sucesso")
	})
	void inativarMultiplos(
			@ApiParam(name = "corpo", value = "IDs de empresas", required = true)
			List<Long> empresaIds);

	@ApiOperation(value = "Lista empresas")
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nome da projeção de pedidos", allowableValues = "apenas-nome",
				name = "projecao", paramType = "query", type = "string")
	})
	CollectionModel<EmpresaBasicoModel> listar();
	
	@ApiIgnore
	@ApiOperation(value = "Lista empresas", hidden = true)
	CollectionModel<EmpresaApenasNomeModel> listarApenasNomes();

}
