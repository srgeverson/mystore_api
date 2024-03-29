package br.com.mystore.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mystore.api.ResourceUriHelper;
import br.com.mystore.api.v1.assembler.CidadeInputDisassembler;
import br.com.mystore.api.v1.assembler.CidadeModelAssembler;
import br.com.mystore.api.v1.model.CidadeModel;
import br.com.mystore.api.v1.model.imput.CidadeInput;
import br.com.mystore.api.v1.openapi.controller.CidadeControllerOpenApi;
import br.com.mystore.core.security.CheckSecurity;
import br.com.mystore.domain.exception.EstadoNaoEncontradoException;
import br.com.mystore.domain.exception.NegocioException;
import br.com.mystore.domain.model.Cidade;
import br.com.mystore.domain.repository.CidadeRepository;
import br.com.mystore.domain.service.CadastroCidadeService;

@RestController
@RequestMapping(path = "/v1/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {
	
	@Autowired
	private CadastroCidadeService cadastroCidade;

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CidadeInputDisassembler cidadeInputDisassembler;

	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;

	@CheckSecurity.Cidades.PodeGerenciar
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	@Override
	public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);

			cidade = cadastroCidade.salvar(cidade);

			CidadeModel cidadeModel = cidadeModelAssembler.toModel(cidade);

			ResourceUriHelper.addUriInResponseHeader(cidadeModel.getId());

			return cidadeModel;
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@CheckSecurity.Cidades.PodeGerenciar
	@PutMapping("/{cidadeId}")
	@Override
	public CidadeModel atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);

			cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);

			cidadeAtual = cadastroCidade.salvar(cidadeAtual);

			return cidadeModelAssembler.toModel(cidadeAtual);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@CheckSecurity.Cidades.PodeGerenciar
	@GetMapping("/{cidadeId}")
	@Override
	public CidadeModel buscar(@PathVariable Long cidadeId) {
		Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);

		return cidadeModelAssembler.toModel(cidade);
	}

	@CheckSecurity.Cidades.PodeGerenciar
	@GetMapping
	@Override
	public CollectionModel<CidadeModel> listar() {
		List<Cidade> todasCidades = cidadeRepository.findAll();

		return cidadeModelAssembler.toCollectionModel(todasCidades);
	}
	
	@CheckSecurity.Cidades.PodeGerenciar
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{cidadeId}")
	@Override
	public void remover(@PathVariable Long cidadeId) {
		cadastroCidade.excluir(cidadeId);
	}

}
