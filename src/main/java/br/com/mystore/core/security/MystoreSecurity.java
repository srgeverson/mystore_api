package br.com.mystore.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import br.com.mystore.domain.repository.EmpresaRepository;
import br.com.mystore.domain.repository.PedidoRepository;

@Component
public class MystoreSecurity {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public boolean hasAuthority(String authorityName) {
		return getAuthentication().getAuthorities().stream()
				.anyMatch(authority -> authority.getAuthority().equals(authorityName));
	}

	public boolean isAutenticado() {
		return getAuthentication().isAuthenticated();
	}

	public Long getUsuarioId() {
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();

		return jwt.getClaim("usuarios_id");
	}

	public boolean podeConsultarEmpresas() {
		return temEscopoLeitura() && isAutenticado();
	}

	public boolean podeGerenciarCadastroEmpresas() {
		return temEscopoEscrita() && hasAuthority("EDITAR_EMPRESAS");
	}

	public boolean podeGerenciarFuncionamentoEmpresas(Long empresaId) {
		return temEscopoEscrita() && (hasAuthority("EDITAR_EMPRESAS") || gerenciaEmpresa(empresaId));
	}

	public boolean podeConsultarFormasPagamento() {
		return isAutenticado() && temEscopoLeitura();
	}

	public boolean podeGerenciarCidades() {
		return temEscopoEscrita() && hasAuthority("EDITAR_CIDADES");
	}

	public boolean gerenciaEmpresa(Long empresaId) {
		if (empresaId == null) {
			return false;
		}

		return empresaRepository.existsResponsavel(empresaId, getUsuarioId());
	}

	public boolean podeGerenciarEstados() {
		return temEscopoEscrita() && hasAuthority("EDITAR_ESTADOS");
	}

	public boolean podeGerenciarFormasPagamento() {
		return temEscopoEscrita() && hasAuthority("EDITAR_FORMAS_PAGAMENTOS");
	}

	public boolean podeGerenciarHost() {
		return temEscopoEscrita() && hasAuthority("GERENCIAR_HOSTS");
	}

	public boolean podeGerenciarUsuariosGruposPermissoes() {
		return temEscopoEscrita() && hasAuthority("EDITAR_USUARIOS_GRUPOS_PERMISSOES");
	}

	public boolean podeConsultarCidades() {
		return isAutenticado() && temEscopoLeitura();
	}

	public boolean podeConsultarEstados() {
		return isAutenticado() && temEscopoLeitura();
	}

	public boolean podeConsultarEstatisticas() {
		return temEscopoLeitura() && hasAuthority("GERAR_RELATORIOS");
	}

	public boolean temEscopoEscrita() {
		return hasAuthority("SCOPE_WRITE");
	}

	public boolean temEscopoLeitura() {
		return hasAuthority("SCOPE_READ");
	}

	public boolean usuarioAutenticadoIgual(Long usuarioId) {
		return getUsuarioId() != null && usuarioId != null && getUsuarioId().equals(usuarioId);
	}

	//Ordenar
	public boolean gerenciaEmpresaDoPedido(String codigoPedido) {
		return pedidoRepository.isPedidoGerenciadoPor(codigoPedido);
	}

	public boolean podePesquisarPedidos() {
		return isAutenticado() && temEscopoLeitura();
	}
	
	public boolean podeGerenciarPedidos(String codigoPedido) {
		return temEscopoEscrita() && (hasAuthority("GERENCIAR_PEDIDOS")
				|| gerenciaEmpresaDoPedido(codigoPedido));
	}
	
	public boolean podeConsultarUsuariosGruposPermissoes() {
		return temEscopoLeitura() && hasAuthority("CONSULTAR_USUARIOS_GRUPOS_PERMISSOES");
	}
	
}
