package br.com.mystore.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.mystore.api.v1.MystoreLinks;
import br.com.mystore.api.v1.controller.CidadeController;
import br.com.mystore.api.v1.model.CidadeModel;
import br.com.mystore.core.security.MystoreSecurity;
import br.com.mystore.domain.model.Cidade;

@Component
public class CidadeModelAssembler 
		extends RepresentationModelAssemblerSupport<Cidade, CidadeModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private  MystoreLinks mystoreLinks;
	
	@Autowired
	private  MystoreSecurity mystoreSecurity;
	
	public CidadeModelAssembler() {
		super(CidadeController.class, CidadeModel.class);
	}
	
	@Override
	public CidadeModel toModel(Cidade cidade) {
		CidadeModel cidadeModel = createModelWithId(cidade.getId(), cidade);
		
		modelMapper.map(cidade, cidadeModel);
		
		if (mystoreSecurity.podeConsultarCidades()) {
			cidadeModel.add(mystoreLinks.linkToCidades("cidades"));
		}
		
		if (mystoreSecurity.podeConsultarEstados()) {
			cidadeModel.getEstado().add(mystoreLinks.linkToEstado(cidadeModel.getEstado().getId()));
		}
		
		return cidadeModel;
	}
	
	@Override
	public CollectionModel<CidadeModel> toCollectionModel(Iterable<? extends Cidade> entities) {
		CollectionModel<CidadeModel> collectionModel = super.toCollectionModel(entities);
		
		if (mystoreSecurity.podeConsultarCidades()) {
			collectionModel.add(mystoreLinks.linkToCidades());
		}
		
		return collectionModel;
	}
	
}
