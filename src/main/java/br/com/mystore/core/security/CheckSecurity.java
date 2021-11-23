package br.com.mystore.core.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

	public @interface Cidades {

		@PreAuthorize("@mystoreSecurity.podeGerenciarCidades()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciar {
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

		@PreAuthorize("hasAuthority('SCOPE_READ') and @mystoreSecurity.usuarioAutenticadoIgual(#usuarioId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeVisualizarProprioUsuario {
		}

	}


}
