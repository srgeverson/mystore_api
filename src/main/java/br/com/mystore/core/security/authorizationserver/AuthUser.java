package br.com.mystore.core.security.authorizationserver;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.mystore.domain.model.Usuario;
import lombok.Getter;

@Getter
public class AuthUser extends User {

	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private String fullName;
	private Object[] empresas;
	
	public AuthUser(Usuario usuario, Collection<? extends GrantedAuthority> authorities, List<Long> empresas) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		
		this.userId = usuario.getId();
		this.fullName = usuario.getNome();
		this.empresas = empresas.toArray();
	}
	
}
