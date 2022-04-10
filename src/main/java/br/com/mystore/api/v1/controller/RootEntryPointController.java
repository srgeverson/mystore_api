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

		//Cidades
		if (mystoreSecurity.podeConsultarCidades()) {
			rootEntryPointModel.add(mystoreLinks.linkToCidades("cidades"));
		}

		// Validar regra de Clientes
		// if (mystoreSecurity.podeGerenciarCLinetes())
		rootEntryPointModel.add(mystoreLinks.linkToClientes(mystoreSecurity.getEmpresas().get(0), "clientes"));

		//Empresas
		if (mystoreSecurity.podeConsultarEmpresas())
			rootEntryPointModel.add(mystoreLinks.linkToEmpresas("empresas"));

		//Estados
		if (mystoreSecurity.podeConsultarEstados()) {
			rootEntryPointModel.add(mystoreLinks.linkToEstados("estados"));
		}

		//Formas de Pagamento
		if (mystoreSecurity.podeConsultarFormasPagamento()) {
			rootEntryPointModel.add(mystoreLinks.linkToFormasPagamento("formas-pagamento"));
		}

		// Validar regra de Produtos
		// if (mystoreSecurity.podeGerenciarProdutos())
		rootEntryPointModel.add(mystoreLinks.linkToProdutos(mystoreSecurity.getEmpresas().get(0), "produtos"));
		
		// Validar regra de Pedidos(Vendas)
		// if (mystoreSecurity.podeGerenciarPedidos())
		rootEntryPointModel.add(mystoreLinks.linkToPedidos("pedidos"));
		
		// Estatisticas
		if (mystoreSecurity.podeConsultarEstatisticas()) {
			rootEntryPointModel.add(mystoreLinks.linkToEstatisticas("estatisticas"));
		}
		
		//Usuário
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
