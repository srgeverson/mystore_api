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
import br.com.mystore.api.v1.assembler.GrupoModelAssembler;
import br.com.mystore.api.v1.model.GrupoModel;
import br.com.mystore.api.v1.openapi.controller.UsuarioGrupoControllerOpenApi;
import br.com.mystore.core.security.CheckSecurity;
import br.com.mystore.core.security.MystoreSecurity;
import br.com.mystore.domain.model.Usuario;
import br.com.mystore.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(path = "/v1/usuarios/{usuarioId}/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi {

	@Autowired
	private CadastroUsuarioService cadastroUsuario;

	@Autowired
	private GrupoModelAssembler grupoModelAssembler;

	@Autowired
	private MystoreLinks mystoreLinks;

	@Autowired
	private MystoreSecurity mystoreSecurity;

	@CheckSecurity.UsuariosGruposPermissoes.PodeGerenciarUsuariosGruposPermissoes
	@PutMapping("/{grupoId}")
	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		cadastroUsuario.associarGrupo(usuarioId, grupoId);
		
		return ResponseEntity.noContent().build();
	}

	@CheckSecurity.UsuariosGruposPermissoes.PodeGerenciarUsuariosGruposPermissoes
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{grupoId}")
	@Override
	public ResponseEntity<Void> desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		cadastroUsuario.desassociarGrupo(usuarioId, grupoId);

		return ResponseEntity.noContent().build();
	}

	@CheckSecurity.UsuariosGruposPermissoes.PodeGerenciarUsuariosGruposPermissoes
	@GetMapping
	@Override
	public CollectionModel<GrupoModel> listar(@PathVariable Long usuarioId) {
		Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);

		CollectionModel<GrupoModel> gruposModel = grupoModelAssembler.toCollectionModel(usuario.getGrupos())
				.removeLinks();

		if (mystoreSecurity.podeGerenciarUsuariosGruposPermissoes()) {
			gruposModel.add(mystoreLinks.linkToUsuarioGrupoAssociacao(usuarioId, "associar"));

			gruposModel.getContent().forEach(grupoModel -> {
				grupoModel.add(
						mystoreLinks.linkToUsuarioGrupoDesassociacao(usuarioId, grupoModel.getId(), "desassociar"));
			});
		}

		return gruposModel;
	}

}
