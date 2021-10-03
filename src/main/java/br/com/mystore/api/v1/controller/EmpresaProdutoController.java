package br.com.mystore.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mystore.api.v1.MystoreLinks;
import br.com.mystore.api.v1.assembler.ProdutoInputDisassembler;
import br.com.mystore.api.v1.assembler.ProdutoModelAssembler;
import br.com.mystore.api.v1.model.ProdutoModel;
import br.com.mystore.api.v1.model.imput.ProdutoInput;
import br.com.mystore.api.v1.openapi.controller.EmpresaProdutoControllerOpenApi;
import br.com.mystore.core.security.CheckSecurity;
import br.com.mystore.domain.model.Empresa;
import br.com.mystore.domain.model.Produto;
import br.com.mystore.domain.repository.ProdutoRepository;
import br.com.mystore.domain.service.CadastroEmpresaService;
import br.com.mystore.domain.service.CadastroProdutoService;

@RestController
@RequestMapping(path = "/v1/empresas/{empresaId}/produtos", 
	produces = MediaType.APPLICATION_JSON_VALUE)
public class EmpresaProdutoController implements EmpresaProdutoControllerOpenApi {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CadastroProdutoService cadastroProduto;
	
	@Autowired
	private CadastroEmpresaService cadastroEmpresa;
	
	@Autowired
	private ProdutoModelAssembler produtoModelAssembler;
	
	@Autowired
	private ProdutoInputDisassembler produtoInputDisassembler;
	
	@Autowired
	private  MystoreLinks algaLinks;
	
	@CheckSecurity.Empresas.PodeConsultar
	@GetMapping
	@Override
	public CollectionModel<ProdutoModel> listar(@PathVariable Long empresaId,
			@RequestParam(required = false, defaultValue = "false") Boolean incluirInativos) {
		Empresa empresa = cadastroEmpresa.buscarOuFalhar(empresaId);
		
		List<Produto> todosProdutos = null;
		
		if (incluirInativos) {
			todosProdutos = produtoRepository.findTodosByEmpresa(empresa);
		} else {
			todosProdutos = produtoRepository.findAtivosByEmpresa(empresa);
		}
		
		return produtoModelAssembler.toCollectionModel(todosProdutos)
				.add(algaLinks.linkToProdutos(empresaId));
	}
	
	@CheckSecurity.Empresas.PodeConsultar
	@Override
	@GetMapping("/{produtoId}")
	public ProdutoModel buscar(@PathVariable Long empresaId, @PathVariable Long produtoId) {
		Produto produto = cadastroProduto.buscarOuFalhar(empresaId, produtoId);
		
		return produtoModelAssembler.toModel(produto);
	}
	
	@CheckSecurity.Empresas.PodeConsultar
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoModel adicionar(@PathVariable Long empresaId,
			@RequestBody @Valid ProdutoInput produtoInput) {
		Empresa empresa = cadastroEmpresa.buscarOuFalhar(empresaId);
		
		Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
		produto.setEmpresa(empresa);
		
		produto = cadastroProduto.salvar(produto);
		
		return produtoModelAssembler.toModel(produto);
	}
	
	@CheckSecurity.Empresas.PodeConsultar
	@Override
	@PutMapping("/{produtoId}")
	public ProdutoModel atualizar(@PathVariable Long empresaId, @PathVariable Long produtoId,
			@RequestBody @Valid ProdutoInput produtoInput) {
		Produto produtoAtual = cadastroProduto.buscarOuFalhar(empresaId, produtoId);
		
		produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);
		
		produtoAtual = cadastroProduto.salvar(produtoAtual);
		
		return produtoModelAssembler.toModel(produtoAtual);
	}
	
}
