package br.com.mystore.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mystore.api.v1.assembler.UsuarioInputDisassembler;
import br.com.mystore.api.v1.assembler.UsuarioModelAssembler;
import br.com.mystore.api.v1.model.UsuarioModel;
import br.com.mystore.api.v1.model.imput.SenhaInput;
import br.com.mystore.api.v1.model.imput.UsuarioComSenhaInput;
import br.com.mystore.api.v1.model.imput.UsuarioInput;
import br.com.mystore.api.v1.openapi.controller.UsuarioControllerOpenApi;
import br.com.mystore.core.security.CheckSecurity;
import br.com.mystore.domain.model.Usuario;
import br.com.mystore.domain.repository.UsuarioRepository;
import br.com.mystore.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(path = "/v1/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements UsuarioControllerOpenApi {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuario;
	
	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;
	
	@Autowired
	private UsuarioInputDisassembler usuarioInputDisassembler;
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@Override
	@GetMapping
	public CollectionModel<UsuarioModel> listar() {
		List<Usuario> todasUsuarios = usuarioRepository.findAll();
		
		return usuarioModelAssembler.toCollectionModel(todasUsuarios);
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@Override
	@GetMapping("/{usuarioId}")
	public UsuarioModel buscar(@PathVariable Long usuarioId) {
		Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);
		
		return usuarioModelAssembler.toModel(usuario);
	}
	
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioModel adicionar(@RequestBody @Valid UsuarioComSenhaInput usuarioInput) {
		Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
		usuario = cadastroUsuario.salvar(usuario);
		
		return usuarioModelAssembler.toModel(usuario);
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeAlterarUsuario
	@Override
	@PutMapping("/{usuarioId}")
	public UsuarioModel atualizar(@PathVariable Long usuarioId,
			@RequestBody @Valid UsuarioInput usuarioInput) {
		Usuario usuarioAtual = cadastroUsuario.buscarOuFalhar(usuarioId);
		usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
		usuarioAtual = cadastroUsuario.salvar(usuarioAtual);
		
		return usuarioModelAssembler.toModel(usuarioAtual);
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeAlterarPropriaSenha
	@Override
	@PutMapping("/{usuarioId}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senha) {
		cadastroUsuario.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
	}
	
}
