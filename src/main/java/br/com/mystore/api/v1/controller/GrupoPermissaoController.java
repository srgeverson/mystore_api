package br.com.mystore.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mystore.api.v1.MystoreLinks;
import br.com.mystore.api.v1.assembler.PermissaoModelAssembler;
import br.com.mystore.api.v1.model.PermissaoModel;
import br.com.mystore.api.v1.openapi.controller.GrupoPermissaoControllerOpenApi;
import br.com.mystore.core.security.CheckSecurity;
import br.com.mystore.core.security.MystoreSecurity;
import br.com.mystore.domain.model.Grupo;
import br.com.mystore.domain.service.CadastroGrupoService;

@RestController
@RequestMapping(path = "/v1/grupos/{grupoId}/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {

	@Autowired
	private CadastroGrupoService cadastroGrupo;

	@Autowired
	private MystoreLinks mystoreLinks;

	@Autowired
	private MystoreSecurity mystoreSecurity;
	
	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;

	@CheckSecurity.UsuariosGruposPermissoes.PodeAlterarGrupo
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{permissaoId}")
	@Override
	public ResponseEntity<Void> associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupo.associarPermissao(grupoId, permissaoId);

		return ResponseEntity.noContent().build();
	}

	@CheckSecurity.UsuariosGruposPermissoes.PodeAlterarGrupo
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{permissaoId}")
	@Override
	public ResponseEntity<Void> desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupo.desassociarPermissao(grupoId, permissaoId);

		return ResponseEntity.noContent().build();
	}

	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@GetMapping
	@Override
	public CollectionModel<PermissaoModel> listar(@PathVariable Long grupoId) {
		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);

		CollectionModel<PermissaoModel> permissoesModel = permissaoModelAssembler
				.toCollectionModel(grupo.getPermissoes()).removeLinks();

		permissoesModel.add(mystoreLinks.linkToGrupoPermissoes(grupoId));

		if (mystoreSecurity.podeGerenciarUsuariosGruposPermissoes()) {
			permissoesModel.add(mystoreLinks.linkToGrupoPermissaoAssociacao(grupoId, "associar"));

			permissoesModel.getContent().forEach(permissaoModel -> {
				permissaoModel.add(
						mystoreLinks.linkToGrupoPermissaoDesassociacao(grupoId, permissaoModel.getId(), "desassociar"));
			});
		}

		return permissoesModel;
	}

}
