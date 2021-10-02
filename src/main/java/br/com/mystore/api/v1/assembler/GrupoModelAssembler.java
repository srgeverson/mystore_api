package br.com.mystore.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.mystore.api.v1.MystoreLinks;
import br.com.mystore.api.v1.controller.GrupoController;
import br.com.mystore.api.v1.model.GrupoModel;
import br.com.mystore.core.security.MystoreSecurity;
import br.com.mystore.domain.model.Grupo;

@Component
public class GrupoModelAssembler extends RepresentationModelAssemblerSupport<Grupo, GrupoModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MystoreLinks mystoreLinks;

	@Autowired
	private MystoreSecurity mystoreSecurity;

	public GrupoModelAssembler() {
		super(GrupoController.class, GrupoModel.class);
	}

	@Override
	public GrupoModel toModel(Grupo grupo) {
		GrupoModel grupoModel = createModelWithId(grupo.getId(), grupo);
		modelMapper.map(grupo, grupoModel);

		if (mystoreSecurity.podeGerenciarUsuariosGruposPermissoes()) {
			grupoModel.add(mystoreLinks.linkToGrupos("grupos"));

			grupoModel.add(mystoreLinks.linkToGrupoPermissoes(grupo.getId(), "permissoes"));
		}

		return grupoModel;
	}

	@Override
	public CollectionModel<GrupoModel> toCollectionModel(Iterable<? extends Grupo> entities) {
		CollectionModel<GrupoModel> collectionModel = super.toCollectionModel(entities);

		if (mystoreSecurity.podeGerenciarUsuariosGruposPermissoes()) {
			collectionModel.add(mystoreLinks.linkToGrupos());
		}

		return collectionModel;
	}

}
