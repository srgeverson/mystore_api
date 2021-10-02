package br.com.mystore.api.v1;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import br.com.mystore.api.v1.controller.EmpresaController;
import br.com.mystore.api.v1.controller.GrupoController;
import br.com.mystore.api.v1.controller.GrupoPermissaoController;
import br.com.mystore.api.v1.controller.PermissaoController;
import br.com.mystore.api.v1.controller.UsuarioController;
import br.com.mystore.api.v1.controller.UsuarioGrupoController;

@Component
public class MystoreLinks {

	public static final TemplateVariables PAGINACAO_VARIABLES = new TemplateVariables(
			new TemplateVariable("page", VariableType.REQUEST_PARAM),
			new TemplateVariable("size", VariableType.REQUEST_PARAM),
			new TemplateVariable("sort", VariableType.REQUEST_PARAM));

	public static final TemplateVariables PROJECAO_VARIABLES = new TemplateVariables(
			new TemplateVariable("projecao", VariableType.REQUEST_PARAM));

	public Link linkToUsuarios(String rel) {
		return linkTo(UsuarioController.class).withRel(rel);
	}

	public Link linkToGrupoPermissaoAssociacao(Long grupoId, String rel) {
		return linkTo(methodOn(GrupoPermissaoController.class).associar(grupoId, null)).withRel(rel);
	}

	public Link linkToGrupoPermissaoDesassociacao(Long grupoId, Long permissaoId, String rel) {
		return linkTo(methodOn(GrupoPermissaoController.class).desassociar(grupoId, permissaoId)).withRel(rel);
	}

	public Link linkToGrupos() {
		return linkToGrupos(IanaLinkRelations.SELF.value());
	}

	public Link linkToGrupos(String rel) {
		return linkTo(GrupoController.class).withRel(rel);
	}

	public Link linkToGruposUsuario(Long usuarioId) {
		return linkToGruposUsuario(usuarioId, IanaLinkRelations.SELF.value());
	}

	public Link linkToGruposUsuario(Long usuarioId, String rel) {
		return linkTo(methodOn(UsuarioGrupoController.class).listar(usuarioId)).withRel(rel);
	}

	public Link linkToPermissoes() {
		return linkToPermissoes(IanaLinkRelations.SELF.value());
	}

	public Link linkToPermissoes(String rel) {
		return linkTo(PermissaoController.class).withRel(rel);
	}

	public Link linkToGrupoPermissoes(Long grupoId) {
		return linkToGrupoPermissoes(grupoId, IanaLinkRelations.SELF.value());
	}

	public Link linkToGrupoPermissoes(Long grupoId, String rel) {
		return linkTo(methodOn(GrupoPermissaoController.class).listar(grupoId)).withRel(rel);
	}

	// Início Links Empresa
	public Link linkToEmpresa(Long empresaId, String rel) {
		return linkTo(methodOn(EmpresaController.class).buscar(empresaId)).withRel(rel);
	}

	public Link linkToEmpresa(Long empresaId) {
		return linkToEmpresa(empresaId, IanaLinkRelations.SELF.value());
	}

	public Link linkToEmpresaInativacao(Long empresaId, String rel) {
		return linkTo(methodOn(EmpresaController.class).inativar(empresaId)).withRel(rel);
	}

	public Link linkToEmpresaAtivacao(Long empresaId, String rel) {
		return linkTo(methodOn(EmpresaController.class).ativar(empresaId)).withRel(rel);
	}

	public Link linkToEmpresas(String rel) {
		String empresasUrl = linkTo(EmpresaController.class).toUri().toString();

		return new Link(UriTemplate.of(empresasUrl, PROJECAO_VARIABLES), rel);
	}

	public Link linkToEmpresas() {
		return linkToEmpresas(IanaLinkRelations.SELF.value());
	}

//	public Link linkToEmpresaFormasPagamento(Long empresaId, String rel) {
//		return linkTo(methodOn(EmpresaFormaPagamentoController.class).listar(empresaId)).withRel(rel);
//	}

//	public Link linkToEmpresaFormasPagamento(Long empresaId) {
//		return linkToEmpresaFormasPagamento(empresaId, IanaLinkRelations.SELF.value());
//	}

//	public Link linkToEmpresaFormaPagamentoDesassociacao(Long empresaId, Long formaPagamentoId, String rel) {
//
//		return linkTo(methodOn(EmpresaFormaPagamentoController.class).desassociar(empresaId, formaPagamentoId))
//				.withRel(rel);
//	}
//
//	public Link linkToEmpresaFormaPagamentoAssociacao(Long empresaId, String rel) {
//		return linkTo(methodOn(EmpresaFormaPagamentoController.class).associar(empresaId, null)).withRel(rel);
//	}

	// Fim Links Empresa

	public Link linkToUsuario(Long usuarioId) {
		return linkToUsuario(usuarioId, IanaLinkRelations.SELF.value());
	}

	public Link linkToUsuario(Long usuarioId, String rel) {
		return linkTo(methodOn(UsuarioController.class).buscar(usuarioId)).withRel(rel);
	}

	public Link linkToUsuarioGrupoAssociacao(Long usuarioId, String rel) {
		return linkTo(methodOn(UsuarioGrupoController.class).associar(usuarioId, null)).withRel(rel);
	}

	public Link linkToUsuarioGrupoDesassociacao(Long usuarioId, Long grupoId, String rel) {
		return linkTo(methodOn(UsuarioGrupoController.class).desassociar(usuarioId, grupoId)).withRel(rel);
	}

	public Link linkToUsuarios() {
		return linkToUsuarios(IanaLinkRelations.SELF.value());
	}

}
