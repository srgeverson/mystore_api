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

import br.com.mystore.api.v1.assembler.GrupoInputDisassembler;
import br.com.mystore.api.v1.assembler.GrupoModelAssembler;
import br.com.mystore.api.v1.model.GrupoModel;
import br.com.mystore.api.v1.model.imput.GrupoInput;
import br.com.mystore.api.v1.openapi.controller.GrupoControllerOpenApi;
import br.com.mystore.core.security.CheckSecurity;
import br.com.mystore.domain.model.Grupo;
import br.com.mystore.domain.repository.GrupoRepository;
import br.com.mystore.domain.service.CadastroGrupoService;

@RestController
@RequestMapping(path = "/v1/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {

	@Autowired
	private GrupoInputDisassembler grupoInputDisassembler;

	@Autowired
	private CadastroGrupoService cadastroGrupo;

	@Autowired
	private GrupoModelAssembler grupoModelAssembler;
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeGerenciarUsuariosGruposPermissoes
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);

		grupo = cadastroGrupo.salvar(grupo);

		return grupoModelAssembler.toModel(grupo);
	}

	@CheckSecurity.UsuariosGruposPermissoes.PodeGerenciarUsuariosGruposPermissoes
	@PutMapping("/{grupoId}")
	@Override
	public GrupoModel atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupoAtual = cadastroGrupo.buscarOuFalhar(grupoId);

		grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);

		grupoAtual = cadastroGrupo.salvar(grupoAtual);

		return grupoModelAssembler.toModel(grupoAtual);
	}

	@CheckSecurity.UsuariosGruposPermissoes.PodeGerenciarUsuariosGruposPermissoes
	@GetMapping("/{grupoId}")
	@Override
	public GrupoModel buscar(@PathVariable Long grupoId) {
		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);

		return grupoModelAssembler.toModel(grupo);
	}

	@CheckSecurity.UsuariosGruposPermissoes.PodeGerenciarUsuariosGruposPermissoes
	@GetMapping
	@Override
	public CollectionModel<GrupoModel> listar() {
		List<Grupo> todosGrupos = grupoRepository.findAll();

		return grupoModelAssembler.toCollectionModel(todosGrupos);
	}

	@CheckSecurity.UsuariosGruposPermissoes.PodeGerenciarUsuariosGruposPermissoes
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{grupoId}")
	@Override
	public void remover(@PathVariable Long grupoId) {
		cadastroGrupo.excluir(grupoId);
	}

}
