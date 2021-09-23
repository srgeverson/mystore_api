package br.com.mystore.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class MystoreSecurity {

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public boolean isAutenticado() {
		return getAuthentication().isAuthenticated();
	}

	public Long getUsuarioId() {
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();

		return jwt.getClaim("usuario_id");
	}

	public boolean usuarioAutenticadoIgual(Long usuarioId) {
		return getUsuarioId() != null && usuarioId != null && getUsuarioId().equals(usuarioId);
	}

	public boolean hasAuthority(String authorityName) {
		return getAuthentication().getAuthorities().stream()
				.anyMatch(authority -> authority.getAuthority().equals(authorityName));
	}

	public boolean temEscopoEscrita() {
		return hasAuthority("SCOPE_WRITE");
	}

	public boolean temEscopoLeitura() {
		return hasAuthority("SCOPE_READ");
	}

	public boolean podeConsultarEmpresas() {
		return temEscopoLeitura() && isAutenticado();
	}

	public boolean podeGerenciarCadastroEmpresas() {
		return temEscopoEscrita() && hasAuthority("EDITAR_EMPRESAS");
	}

	public boolean podeConsultarUsuariosGruposPermissoes() {
		return temEscopoLeitura() && hasAuthority("CONSULTAR_USUARIOS_GRUPOS_PERMISSOES");
	}

	public boolean podeEditarUsuariosGruposPermissoes() {
		return temEscopoEscrita() && hasAuthority("EDITAR_USUARIOS_GRUPOS_PERMISSOES");
	}

	public boolean podePesquisarPedidos() {
		return isAutenticado() && temEscopoLeitura();
	}

	public boolean podeConsultarFormasPagamento() {
		return isAutenticado() && temEscopoLeitura();
	}

	public boolean podeConsultarCidades() {
		return isAutenticado() && temEscopoLeitura();
	}

	public boolean podeConsultarEstados() {
		return isAutenticado() && temEscopoLeitura();
	}

	public boolean podeConsultarCozinhas() {
		return isAutenticado() && temEscopoLeitura();
	}

	public boolean podeConsultarEstatisticas() {
		return temEscopoLeitura() && hasAuthority("GERAR_RELATORIOS");
	}

}
