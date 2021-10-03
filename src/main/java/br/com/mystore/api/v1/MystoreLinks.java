package br.com.mystore.api.v1;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.stereotype.Component;

import br.com.mystore.api.v1.controller.CidadeController;
import br.com.mystore.api.v1.controller.EmpresaController;
import br.com.mystore.api.v1.controller.EmpresaFormaPagamentoController;
import br.com.mystore.api.v1.controller.EmpresaProdutoController;
import br.com.mystore.api.v1.controller.EstadoController;
import br.com.mystore.api.v1.controller.FormaPagamentoController;
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

	// Início Links Cidades
	public Link linkToCidade(Long cidadeId, String rel) {
		return linkTo(methodOn(CidadeController.class).buscar(cidadeId)).withRel(rel);
	}

	public Link linkToCidade(Long cidadeId) {
		return linkToCidade(cidadeId, IanaLinkRelations.SELF.value());
	}

	public Link linkToCidades(String rel) {
		return linkTo(CidadeController.class).withRel(rel);
	}

	public Link linkToCidades() {
		return linkToCidades(IanaLinkRelations.SELF.value());
	}
	// Fim Links Cidades

	// Início Links Estados
	public Link linkToEstado(Long estadoId, String rel) {
		return linkTo(methodOn(EstadoController.class).buscar(estadoId)).withRel(rel);
	}

	public Link linkToEstado(Long estadoId) {
		return linkToEstado(estadoId, IanaLinkRelations.SELF.value());
	}

	public Link linkToEstados(String rel) {
		return linkTo(EstadoController.class).withRel(rel);
	}

	public Link linkToEstados() {
		return linkToEstados(IanaLinkRelations.SELF.value());
	}
	// Início Links Estados

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

	public Link linkToEmpresas() {
		return linkToEmpresas(IanaLinkRelations.SELF.value());
	}

	public Link linkToEmpresas(String rel) {
		return linkTo(EmpresaController.class).withRel(rel);
	}

	public Link linkToEmpresaFormasPagamento(Long empresaId, String rel) {
		return linkTo(methodOn(EmpresaFormaPagamentoController.class).listar(empresaId)).withRel(rel);
	}

	public Link linkToEmpresaFormasPagamento(Long empresaId) {
		return linkToEmpresaFormasPagamento(empresaId, IanaLinkRelations.SELF.value());
	}

	public Link linkToEmpresaFormaPagamentoDesassociacao(Long empresaId, Long formaPagamentoId, String rel) {

		return linkTo(methodOn(EmpresaFormaPagamentoController.class).desassociar(empresaId, formaPagamentoId))
				.withRel(rel);
	}

	public Link linkToEmpresaFormaPagamentoAssociacao(Long empresaId, String rel) {
		return linkTo(methodOn(EmpresaFormaPagamentoController.class).associar(empresaId, null)).withRel(rel);
	}
	// Fim Links Empresa

	// Início Links Formas Pagamentos
	public Link linkToFormaPagamento(Long formaPagamentoId, String rel) {
		return linkTo(methodOn(FormaPagamentoController.class).buscar(formaPagamentoId, null)).withRel(rel);
	}

	public Link linkToFormaPagamento(Long formaPagamentoId) {
		return linkToFormaPagamento(formaPagamentoId, IanaLinkRelations.SELF.value());
	}

	public Link linkToFormasPagamento(String rel) {
		return linkTo(FormaPagamentoController.class).withRel(rel);
	}

	public Link linkToFormasPagamento() {
		return linkToFormasPagamento(IanaLinkRelations.SELF.value());
	}
	// Fim Links Formas Pagamentos

	// Início Links Grupos e Permissoes
	public Link linkToGrupoPermissoes(Long grupoId) {
		return linkToGrupoPermissoes(grupoId, IanaLinkRelations.SELF.value());
	}

	public Link linkToGrupoPermissoes(Long grupoId, String rel) {
		return linkTo(methodOn(GrupoPermissaoController.class).listar(grupoId)).withRel(rel);
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
	// Fim Links Grupos e Permissoes

	// Início Links Produtos
	public Link linkToProduto(Long empresaId, Long produtoId, String rel) {
		return linkTo(methodOn(EmpresaProdutoController.class).buscar(empresaId, produtoId)).withRel(rel);
	}

	public Link linkToProduto(Long empresaId, Long produtoId) {
		return linkToProduto(empresaId, produtoId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToProdutos(Long empresaId, String rel) {
		return linkTo(methodOn(EmpresaProdutoController.class)
				.listar(empresaId, null)).withRel(rel);
	}
	
	public Link linkToProdutos(Long empresaId) {
		return linkToProdutos(empresaId, IanaLinkRelations.SELF.value());
	}
	// Fim Links Produtos

	// Início Links Usuário
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
	// Fim Links Usuário
}
