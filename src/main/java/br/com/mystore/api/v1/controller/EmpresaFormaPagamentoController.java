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
import br.com.mystore.api.v1.assembler.FormaPagamentoModelAssembler;
import br.com.mystore.api.v1.model.FormaPagamentoModel;
import br.com.mystore.api.v1.openapi.controller.EmpresaFormaPagamentoControllerOpenApi;
import br.com.mystore.core.security.CheckSecurity;
import br.com.mystore.core.security.MystoreSecurity;
import br.com.mystore.domain.model.Empresa;
import br.com.mystore.domain.service.CadastroEmpresaService;

@RestController
@RequestMapping(path = "/v1/empresas/{empresaId}/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmpresaFormaPagamentoController implements EmpresaFormaPagamentoControllerOpenApi {

	@Autowired
	private CadastroEmpresaService cadastroEmpresa;

	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

	@Autowired
	private MystoreLinks mystoreLinks;

	@Autowired
	private MystoreSecurity mystoreSecurity;

	@CheckSecurity.Empresas.PodeGerenciar
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{formaPagamentoId}")
	@Override
	public ResponseEntity<Void> associar(@PathVariable Long empresaId, @PathVariable Long formaPagamentoId) {
		cadastroEmpresa.associarFormaPagamento(empresaId, formaPagamentoId);

		return ResponseEntity.noContent().build();
	}

	@CheckSecurity.Empresas.PodeGerenciar
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{formaPagamentoId}")
	@Override
	public ResponseEntity<Void> desassociar(@PathVariable Long empresaId, @PathVariable Long formaPagamentoId) {
		cadastroEmpresa.desassociarFormaPagamento(empresaId, formaPagamentoId);

		return ResponseEntity.noContent().build();
	}

	@CheckSecurity.Empresas.PodeGerenciar
	@GetMapping
	@Override
	public CollectionModel<FormaPagamentoModel> listar(@PathVariable Long empresaId) {
		Empresa empresa = cadastroEmpresa.buscarOuFalhar(empresaId);

		CollectionModel<FormaPagamentoModel> formasPagamentoModel = formaPagamentoModelAssembler
				.toCollectionModel(empresa.getFormasPagamento()).removeLinks();

		formasPagamentoModel.add(mystoreLinks.linkToEmpresaFormasPagamento(empresaId));

		if (mystoreSecurity.podeGerenciarEmpresas(empresaId)) {
			formasPagamentoModel.add(mystoreLinks.linkToEmpresaFormaPagamentoAssociacao(empresaId, "associar"));

			formasPagamentoModel.getContent().forEach(formaPagamentoModel -> {
				formaPagamentoModel.add(mystoreLinks.linkToEmpresaFormaPagamentoDesassociacao(empresaId,
						formaPagamentoModel.getId(), "desassociar"));
			});
		}

		return formasPagamentoModel;
	}
}
