package br.com.mystore.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.mystore.api.v1.MystoreLinks;
import br.com.mystore.api.v1.controller.UsuarioController;
import br.com.mystore.api.v1.model.UsuarioModel;
import br.com.mystore.core.security.MystoreSecurity;
import br.com.mystore.domain.model.Usuario;

@Component
public class UsuarioModelAssembler 
		extends RepresentationModelAssemblerSupport<Usuario, UsuarioModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MystoreLinks mystoreLinks;
	
	@Autowired
	private MystoreSecurity mystoreSecurity;
	
	public UsuarioModelAssembler() {
		super(UsuarioController.class, UsuarioModel.class);
	}
	
	@Override
	public UsuarioModel toModel(Usuario usuario) {
		UsuarioModel usuarioModel = createModelWithId(usuario.getId(), usuario);
		modelMapper.map(usuario, usuarioModel);
		
		if (mystoreSecurity.podeConsultarUsuariosGruposPermissoes()) {
			usuarioModel.add(mystoreLinks.linkToUsuarios("usuarios"));
			
			usuarioModel.add(mystoreLinks.linkToGruposUsuario(usuario.getId(), "grupos-usuario"));
		}
		
		return usuarioModel;
	}
	
	@Override
	public CollectionModel<UsuarioModel> toCollectionModel(Iterable<? extends Usuario> entities) {
		CollectionModel<UsuarioModel> collectionModel = super.toCollectionModel(entities);
		
		if (mystoreSecurity.podeConsultarUsuariosGruposPermissoes()) {
			collectionModel.add(mystoreLinks.linkToUsuarios());
		}
		
		return collectionModel;
	}
	
}
