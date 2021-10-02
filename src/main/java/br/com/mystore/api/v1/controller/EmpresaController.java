package br.com.mystore.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mystore.api.v1.assembler.EmpresaApenasNomeModelAssembler;
import br.com.mystore.api.v1.assembler.EmpresaBasicoModelAssembler;
import br.com.mystore.api.v1.assembler.EmpresaInputDisassembler;
import br.com.mystore.api.v1.assembler.EmpresaModelAssembler;
import br.com.mystore.api.v1.model.EmpresaApenasNomeModel;
import br.com.mystore.api.v1.model.EmpresaBasicoModel;
import br.com.mystore.api.v1.model.EmpresaModel;
import br.com.mystore.api.v1.model.imput.EmpresaInput;
import br.com.mystore.api.v1.openapi.controller.EmpresaControllerOpenApi;
import br.com.mystore.core.security.CheckSecurity;
import br.com.mystore.domain.exception.CidadeNaoEncontradaException;
import br.com.mystore.domain.exception.CozinhaNaoEncontradaException;
import br.com.mystore.domain.exception.EmpresaNaoEncontradaException;
import br.com.mystore.domain.exception.NegocioException;
import br.com.mystore.domain.model.Empresa;
import br.com.mystore.domain.repository.EmpresaRepository;
import br.com.mystore.domain.service.CadastroEmpresaService;

@RestController
@RequestMapping(path = "/v1/empresas", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmpresaController implements EmpresaControllerOpenApi {

	@Autowired
	private EmpresaApenasNomeModelAssembler empresaApenasNomeModelAssembler;

	@Autowired
	private EmpresaBasicoModelAssembler empresaBasicoModelAssembler;

	@Autowired
	private CadastroEmpresaService cadastroEmpresa;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private EmpresaInputDisassembler empresaInputDisassembler;

	@Autowired
	private EmpresaModelAssembler empresaModelAssembler;

	@CheckSecurity.Empresas.PodeGerenciarEmpresa
	@Override
	@GetMapping
	public CollectionModel<EmpresaBasicoModel> listar() {
		return empresaBasicoModelAssembler.toCollectionModel(empresaRepository.findAll());
	}

	@CheckSecurity.Empresas.PodeGerenciarEmpresa
	@Override
	@GetMapping(params = "projecao=apenas-nome")
	public CollectionModel<EmpresaApenasNomeModel> listarApenasNomes() {
		return empresaApenasNomeModelAssembler.toCollectionModel(empresaRepository.findAll());
	}

	@CheckSecurity.Empresas.PodeGerenciarEmpresa
	@Override
	@GetMapping("/{empresaId}")
	public EmpresaModel buscar(@PathVariable Long empresaId) {
		Empresa empresa = cadastroEmpresa.buscarOuFalhar(empresaId);

		return empresaModelAssembler.toModel(empresa);
	}

	@CheckSecurity.Empresas.PodeGerenciarEmpresa
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EmpresaModel adicionar(@RequestBody @Valid EmpresaInput empresaInput) {
		try {
			Empresa empresa = empresaInputDisassembler.toDomainObject(empresaInput);

			return empresaModelAssembler.toModel(cadastroEmpresa.salvar(empresa));
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@CheckSecurity.Empresas.PodeGerenciarEmpresa
	@Override
	@PutMapping("/{empresaId}")
	public EmpresaModel atualizar(@PathVariable Long empresaId, @RequestBody @Valid EmpresaInput empresaInput) {
		try {
			Empresa empresaAtual = cadastroEmpresa.buscarOuFalhar(empresaId);

			empresaInputDisassembler.copyToDomainObject(empresaInput, empresaAtual);

			return empresaModelAssembler.toModel(cadastroEmpresa.salvar(empresaAtual));
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@CheckSecurity.Empresas.PodeGerenciarEmpresa
	@Override
	@PutMapping("/{empresaId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> ativar(@PathVariable Long empresaId) {
		cadastroEmpresa.ativar(empresaId);

		return ResponseEntity.noContent().build();
	}

	@CheckSecurity.Empresas.PodeGerenciarEmpresa
	@Override
	@DeleteMapping("/{empresaId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> inativar(@PathVariable Long empresaId) {
		cadastroEmpresa.inativar(empresaId);

		return ResponseEntity.noContent().build();
	}

	@CheckSecurity.Empresas.PodeGerenciarEmpresa
	@Override
	@PutMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarMultiplos(@RequestBody List<Long> empresaIds) {
		try {
			cadastroEmpresa.ativar(empresaIds);
		} catch (EmpresaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@CheckSecurity.Empresas.PodeGerenciarEmpresa
	@Override
	@DeleteMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativarMultiplos(@RequestBody List<Long> empresaIds) {
		try {
			cadastroEmpresa.inativar(empresaIds);
		} catch (EmpresaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

}
