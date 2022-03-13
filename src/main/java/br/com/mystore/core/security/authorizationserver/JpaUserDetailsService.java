package br.com.mystore.core.security.authorizationserver;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mystore.domain.exception.NegocioException;
import br.com.mystore.domain.model.Usuario;
import br.com.mystore.domain.repository.EmpresaRepository;
import br.com.mystore.domain.repository.UsuarioRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;

	@Transactional(readOnly = false)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AuthUser authUser = null;
		try {
			Usuario usuario = usuarioRepository.getUsuarioAtivoByEmail(username)
					.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com e-mail informado ou este email está desabilitado!"));
			
			authUser = new AuthUser(usuario, getAuthorities(usuario), empresaRepository.empresasPorusuario(usuario.getId()));

			usuario.setDataUltimoAcesso(OffsetDateTime.now());
			usuarioRepository.save(usuario);

		} catch (InvalidGrantException ex) {
			throw new NegocioException("Erro");
		}

		return authUser;
	}

	private Collection<GrantedAuthority> getAuthorities(Usuario usuario) {
		return usuario.getGrupos().stream().flatMap(grupo -> grupo.getPermissoes().stream())
				.map(permissao -> new SimpleGrantedAuthority(permissao.getNome().toUpperCase()))
				.collect(Collectors.toSet());
	}

}
