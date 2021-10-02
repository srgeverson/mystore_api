package br.com.mystore.api.v1.openapi.controller;

import io.swagger.annotations.Api;

@Api(tags = "Produtos")
public interface EmpresaProdutoFotoControllerOpenApi {

//	@ApiOperation("Atualiza a foto do produto de um empresa")
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "Foto do produto atualizada"),
//		@ApiResponse(code = 404, message = "Produto de empresa não encontrado", response = Problem.class)
//	})
//	FotoProdutoModel atualizarFoto(
//			@ApiParam(value = "ID do empresa", example = "1", required = true)
//			Long empresaId,
//			
//			@ApiParam(value = "ID do produto", example = "1", required = true)
//			Long produtoId,
//			
//			FotoProdutoInput fotoProdutoInput,
//			
//			@ApiParam(value = "Arquivo da foto do produto (máximo 500KB, apenas JPG e PNG)",
//				required = true)
//			MultipartFile arquivo) throws IOException;
//
//	@ApiOperation("Exclui a foto do produto de um empresa")
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Foto do produto excluída"),
//		@ApiResponse(code = 400, message = "ID do empresa ou produto inválido", response = Problem.class),
//		@ApiResponse(code = 404, message = "Foto de produto não encontrada", response = Problem.class)
//	})
//	void excluir(
//			@ApiParam(value = "ID do empresa", example = "1", required = true)
//			Long empresaId,
//			
//			@ApiParam(value = "ID do produto", example = "1", required = true)
//			Long produtoId);
//
//	@ApiOperation(value = "Busca a foto do produto de um empresa",
//			produces = "application/json, image/jpeg, image/png")
//	@ApiResponses({
//		@ApiResponse(code = 400, message = "ID do empresa ou produto inválido", response = Problem.class),
//		@ApiResponse(code = 404, message = "Foto de produto não encontrada", response = Problem.class)
//	})
//	FotoProdutoModel buscar(
//			@ApiParam(value = "ID do empresa", example = "1", required = true)
//			Long empresaId,
//			
//			@ApiParam(value = "ID do produto", example = "1", required = true)
//			Long produtoId);
//
//	@ApiOperation(value = "Busca a foto do produto de um empresa", hidden = true)
//	ResponseEntity<?> servir(Long empresaId, Long produtoId, String acceptHeader) 
//			throws HttpMediaTypeNotAcceptableException;

}