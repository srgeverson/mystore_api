package br.com.mystore.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mystore.domain.exception.NegocioException;
import br.com.mystore.domain.exception.UsuarioNaoEncontradoException;
import br.com.mystore.domain.model.Grupo;
import br.com.mystore.domain.model.Usuario;
import br.com.mystore.domain.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {

	@Autowired
	private CadastroGrupoService cadastroGrupo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional
	public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
		Usuario usuario = buscarOuFalhar(usuarioId);

		if (!passwordEncoder.matches(senhaAtual, usuario.getSenha())) {
			throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
		}

		usuario.setSenha(passwordEncoder.encode(novaSenha));
	}

	@Transactional
	public void associarGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario = buscarOuFalhar(usuarioId);
		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);

		usuario.adicionarGrupo(grupo);
	}

	public Usuario buscarOuFalhar(String usuarioEmail) {
		return usuarioRepository.findByEmail(usuarioEmail).orElseThrow(() -> new UsuarioNaoEncontradoException(
				String.format("Não existe um cadastro de usuário com email %s", usuarioEmail)));
	}

	public Usuario buscarOuFalhar(Long usuarioId) {
		return usuarioRepository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
	}

	@Transactional
	public void desassociarGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario = buscarOuFalhar(usuarioId);
		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);

		usuario.removerGrupo(grupo);
	}

	@Transactional
	public Usuario salvar(Usuario usuario) {
		usuarioRepository.detach(usuario);

		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new NegocioException(
					String.format("Já existe um usuário cadastrado com o e-mail %s", usuario.getEmail()));
		}

		if (usuario.isNovo()) {
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			usuario.setAtivo(false);
		}

		return usuarioRepository.save(usuario);
	}

	public Usuario recuperarSenha(String usuarioEmail) {
		Usuario usuario = buscarOuFalhar(usuarioEmail);

		if (usuario.getDataUltimoAcesso() == null) {
			throw new NegocioException(String.format("Este usuário ainda nao acessou o sistema, acesse sua caixa de email e veja as informacoes de primeiro acesso!"));
		}

		//usuario.enviarNovaSenha();

		return usuarioRepository.save(usuario);
	}

	public Usuario validarAcesso(String usuarioEmail) {
		Usuario usuario = buscarOuFalhar(usuarioEmail);

		if (usuario.getAtivo()) {
			throw new NegocioException(String.format("Este usuário já encontra-se validado!"));
		}

		usuario.setAtivo(true);

		return usuarioRepository.save(usuario);
	}

}
