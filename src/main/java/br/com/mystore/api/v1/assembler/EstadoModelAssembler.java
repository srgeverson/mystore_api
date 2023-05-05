package br.com.mystore.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.mystore.api.v1.MystoreLinks;
import br.com.mystore.api.v1.controller.EstadoController;
import br.com.mystore.api.v1.model.EstadoModel;
import br.com.mystore.core.security.MystoreSecurity;
import br.com.mystore.domain.model.Estado;

@Component
public class EstadoModelAssembler 
		extends RepresentationModelAssemblerSupport<Estado, EstadoModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private  MystoreLinks mystoreLinks;
	
	@Autowired
	private  MystoreSecurity mystoreSecurity;
	
	public EstadoModelAssembler() {
		super(EstadoController.class, EstadoModel.class);
	}
	
	@Override
	public EstadoModel toModel(Estado estado) {
		EstadoModel estadoModel = createModelWithId(estado.getId(), estado);
		modelMapper.map(estado, estadoModel);
		
		if (mystoreSecurity.podeConsultarEstados()) {
			estadoModel.add(mystoreLinks.linkToEstados("estados"));
		}
		
		return estadoModel;
	}
	
	@Override
	public CollectionModel<EstadoModel> toCollectionModel(Iterable<? extends Estado> entities) {
		CollectionModel<EstadoModel> collectionModel = super.toCollectionModel(entities);
		
		if (mystoreSecurity.podeConsultarEstados()) {
			collectionModel.add(mystoreLinks.linkToEstados());
		}
		
		return collectionModel;
	}
	
}
