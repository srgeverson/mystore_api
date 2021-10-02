package br.com.mystore.core.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

	public @interface Empresas {

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and @mystoreSecurity.usuarioProprietario(#usuarioId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAlterarPropriaEmpresa {
		}

		@PreAuthorize("@mystoreSecurity.podeGerenciarEmpresas()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciarEmpresa {
		}

	}

	public @interface Estados {

		@PreAuthorize("@mystoreSecurity.podeGerenciarEstados()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciarCidades {
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

	public @interface Cidades {

		@PreAuthorize("@mystoreSecurity.podeGerenciarCidadess()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciarCidades {
		}

	}

}
