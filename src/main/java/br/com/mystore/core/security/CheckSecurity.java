package br.com.mystore.core.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

	public @interface UsuariosGruposPermissoes {

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and @mystoreSecurity.usuarioAutenticadoIgual(#usuarioId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAlterarPropriaSenha {
		}

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and @mystoreSecurity.usuarioAutenticadoIgual(#usuarioId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAlterarUsuario {
		}

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('GERENCIAR_USUARIOS_GRUPOS_PERMISSOES')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeCadastrar {
		}

		@PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('GERENCIAR_USUARIOS_GRUPOS_PERMISSOES')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {
		}

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and @mystoreSecurity.usuarioAutenticadoIgual(#usuarioId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar {
		}
		
		@PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('RECUPERAR_SENHA')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeRecuperarSenha {
		}

		@PreAuthorize("hasAuthority('SCOPE_READ') and @mystoreSecurity.usuarioAutenticadoIgual(#usuarioId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeVisualizar {
		}

	}

}
