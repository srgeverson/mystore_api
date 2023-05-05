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
import br.com.mystore.api.v1.assembler.UsuarioModelAssembler;
import br.com.mystore.api.v1.model.UsuarioModel;
import br.com.mystore.api.v1.openapi.controller.EmpresaUsuarioResponsavelControllerOpenApi;
import br.com.mystore.core.security.CheckSecurity;
import br.com.mystore.core.security.MystoreSecurity;
import br.com.mystore.domain.model.Empresa;
import br.com.mystore.domain.service.CadastroEmpresaService;

@RestController
@RequestMapping(path = "/v1/empresas/{empresaId}/responsaveis", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmpresaUsuarioResponsavelController implements EmpresaUsuarioResponsavelControllerOpenApi {

	@Autowired
	private CadastroEmpresaService cadastroEmpresa;

	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;

	@Autowired
	private MystoreLinks mystoreLinks;

	@Autowired
	private MystoreSecurity mystoreSecurity;

	@CheckSecurity.Empresas.PodeGerenciarCadastro
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{usuarioId}")
	@Override
	public ResponseEntity<Void> associar(@PathVariable Long empresaId, @PathVariable Long usuarioId) {
		cadastroEmpresa.associarResponsavel(empresaId, usuarioId);

		return ResponseEntity.noContent().build();
	}

	@CheckSecurity.Empresas.PodeGerenciarCadastro
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{usuarioId}")
	@Override
	public ResponseEntity<Void> desassociar(@PathVariable Long empresaId, @PathVariable Long usuarioId) {
		cadastroEmpresa.desassociarResponsavel(empresaId, usuarioId);

		return ResponseEntity.noContent().build();
	}

	@CheckSecurity.Empresas.PodeGerenciarCadastro
	@GetMapping
	@Override
	public CollectionModel<UsuarioModel> listar(@PathVariable Long empresaId) {
		Empresa empresa = cadastroEmpresa.buscarOuFalhar(empresaId);

		CollectionModel<UsuarioModel> usuariosModel = usuarioModelAssembler.toCollectionModel(empresa.getResponsaveis())
				.removeLinks();

		usuariosModel.add(mystoreLinks.linkToEmpresaResponsaveis(empresaId));

		if (mystoreSecurity.podeGerenciarCadastroEmpresas()) {
			usuariosModel.add(mystoreLinks.linkToEmpresaResponsavelAssociacao(empresaId, "associar"));

			usuariosModel.getContent().stream().forEach(usuarioModel -> {
				usuarioModel.add(mystoreLinks.linkToEmpresaResponsavelDesassociacao(empresaId, usuarioModel.getId(),
						"desassociar"));
			});
		}

		return usuariosModel;
	}

}
