package br.com.mystore.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import br.com.mystore.api.exceptionhandler.Problema;
import br.com.mystore.api.v1.model.FormaPagamentoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Empresas")
public interface EmpresaFormaPagamentoControllerOpenApi {
	
	@ApiOperation("Lista as formas de pagamento associadas a empresa")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Empresa não encontrado", response = Problema.class)
	})
	CollectionModel<FormaPagamentoModel> listar(
			@ApiParam(value = "ID do empresa", example = "1", required = true)
			Long empresaId);

	@ApiOperation("Desassociação de empresa com forma de pagamento")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
		@ApiResponse(code = 404, message = "Empresa ou forma de pagamento não encontrado", 
			response = Problema.class)
	})
	ResponseEntity<Void> desassociar(
			@ApiParam(value = "ID do empresa", example = "1", required = true)
			Long empresaId,
			
			@ApiParam(value = "ID da forma de pagamento", example = "1", required = true)
			Long formaPagamentoId);

	@ApiOperation("Associação de empresa com forma de pagamento")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Associação realizada com sucesso"),
		@ApiResponse(code = 404, message = "Empresa ou forma de pagamento não encontrado"), 
	})
	ResponseEntity<Void> associar(
			@ApiParam(value = "ID do empresa", example = "1", required = true)
			Long empresaId,
			
			@ApiParam(value = "ID da forma de pagamento", example = "1", required = true)
			Long formaPagamentoId);

}