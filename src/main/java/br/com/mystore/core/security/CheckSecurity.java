package br.com.mystore.core.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

	public @interface Cidades {

		@PreAuthorize("@mystoreSecurity.podeGerenciarCidades()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciar {
		}
		
		@PreAuthorize("@mystoreSecurity.podeConsultarCidades()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {
		}

	}

	public @interface Empresas {

		@PreAuthorize("@mystoreSecurity.podeConsultarEmpresas()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {
		}

		@PreAuthorize("@mystoreSecurity.podeGerenciarCadastroEmpresas()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciarCadastro {
		}

		@PreAuthorize("@mystoreSecurity.podeGerenciarFuncionamentoEmpresas(#empresasId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciarFuncionamento {
		}

	}

	public @interface Estados {

		@PreAuthorize("@mystoreSecurity.podeGerenciarEstados()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciar {
		}
		
		@PreAuthorize("@mystoreSecurity.podeConsultarEstados()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {
		}

	}

	public @interface FormasPagamento {

		@PreAuthorize("@mystoreSecurity.podeConsultarFormasPagamento()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {
		}

		@PreAuthorize("@mystoreSecurity.podeGerenciarFormasPagamento()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciar {
		}

	}

	public @interface Host {

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and @mystoreSecurity.podeGerenciarHost()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciarHosts {
		}

	}

	public @interface Pedidos {

		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@PostAuthorize("hasAuthority('CONSULTAR_PEDIDOS') or "
				+ "@mystoreSecurity.usuarioAutenticadoIgual(returnObject.cliente.id) or "
				+ "@mystoreSecurity.gerenciaEmpresa(returnObject.empresa.id)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeBuscar {
		}

		@PreAuthorize("@mystoreSecurity.podePesquisarPedidos(#filtro.clienteId, #filtro.empresaId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodePesquisar {
		}

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeCriar {
		}

		@PreAuthorize("@mystoreSecurity.podeGerenciarPedidos(#codigoPedido)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciarPedidos {
		}

	}

	public @interface UsuariosGruposPermissoes {

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and @mystoreSecurity.usuarioAutenticadoIgual(#usuarioId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAlterarPropriaSenha {
		}

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and @mystoreSecurity.usuarioAutenticadoIgual(#usuarioId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAlterarProprioUsuario {
		}

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and @mystoreSecurity.podeGerenciarUsuariosGruposPermissoes()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciarUsuariosGruposPermissoes {
		}

		@PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('RECUPERAR_SENHA')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeRecuperarSenha {
		}

		@PreAuthorize("hasAuthority('SCOPE_READ')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeVisualizarProprioUsuario {
		}

	}

}
