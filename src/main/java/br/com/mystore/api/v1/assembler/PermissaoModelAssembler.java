package br.com.mystore.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.mystore.api.v1.MystoreLinks;
import br.com.mystore.api.v1.model.PermissaoModel;
import br.com.mystore.core.security.MystoreSecurity;
import br.com.mystore.domain.model.Permissao;

@Component
public class PermissaoModelAssembler 
		implements RepresentationModelAssembler<Permissao, PermissaoModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MystoreLinks algaLinks;
	
	@Autowired
	private MystoreSecurity mystoreSecurity;

	@Override
	public PermissaoModel toModel(Permissao permissao) {
		PermissaoModel permissaoModel = modelMapper.map(permissao, PermissaoModel.class);
		return permissaoModel;
	}
	
	@Override
	public CollectionModel<PermissaoModel> toCollectionModel(Iterable<? extends Permissao> entities) {
		CollectionModel<PermissaoModel> collectionModel 
			= RepresentationModelAssembler.super.toCollectionModel(entities);

		if (mystoreSecurity.podeConsultarUsuariosGruposPermissoes()) {
			collectionModel.add(algaLinks.linkToPermissoes());
		}
		
		return collectionModel;
	}
	
}
