package br.com.mystore.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mystore.api.v1.model.input.UsuarioComSenhaInput;
import br.com.mystore.api.v1.model.input.UsuarioInput;
import br.com.mystore.domain.model.Usuario;

@Component
public class UsuarioInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Usuario toDomainObject(UsuarioInput usuarioInput) {
		return modelMapper.map(usuarioInput, Usuario.class);
	}

	public Usuario toDomainObject(UsuarioComSenhaInput usuarioComSenhaInput) {
		return modelMapper.map(usuarioComSenhaInput, Usuario.class);
	}

	public void copyToDomainObject(UsuarioInput usuarioInput, Usuario usuario) {
		modelMapper.map(usuarioInput, usuario);
	}

}
