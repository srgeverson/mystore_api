package br.com.mystore.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.mystore.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String email);

	@Query("from usuarios where email = :usuarioEmail and ativo = 1")
	Optional<Usuario> getUsuarioAtivoByEmail(String usuarioEmail);
}
