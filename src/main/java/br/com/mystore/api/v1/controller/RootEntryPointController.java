package br.com.mystore.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mystore.api.v1.MystoreLinks;
import br.com.mystore.core.security.MystoreSecurity;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(path = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

	@Autowired
	private MystoreLinks mystoreLinks;

	@Autowired
	private MystoreSecurity mystoreSecurity;

	@GetMapping
	public RootEntryPointModel root() {
		var rootEntryPointModel = new RootEntryPointModel();

		if (mystoreSecurity.podeConsultarCidades()) {
			rootEntryPointModel.add(mystoreLinks.linkToCidades("cidades"));
		}

		if (mystoreSecurity.podeConsultarEmpresas())
			rootEntryPointModel.add(mystoreLinks.linkToEmpresas("empresas"));

		if (mystoreSecurity.podeConsultarEstados()) {
			rootEntryPointModel.add(mystoreLinks.linkToEstados("estados"));
		}
		
		if (mystoreSecurity.podeConsultarFormasPagamento()) {
			rootEntryPointModel.add(mystoreLinks.linkToFormasPagamento("formas-pagamento"));
		}

		if (mystoreSecurity.podeGerenciarUsuariosGruposPermissoes())
			rootEntryPointModel //
					.add(mystoreLinks.linkToGrupos("grupos")) // Grupos
					.add(mystoreLinks.linkToPermissoes("permissoes")) // Permissões
					.add(mystoreLinks.linkToUsuarios("usuarios")); // Usuários

		return rootEntryPointModel;
	}

	private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {
	}

}
