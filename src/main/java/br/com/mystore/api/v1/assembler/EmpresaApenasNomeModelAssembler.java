package br.com.mystore.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.mystore.api.v1.MystoreLinks;
import br.com.mystore.api.v1.controller.EmpresaController;
import br.com.mystore.api.v1.model.EmpresaApenasNomeModel;
import br.com.mystore.core.security.MystoreSecurity;
import br.com.mystore.domain.model.Empresa;

@Component
public class EmpresaApenasNomeModelAssembler 
		extends RepresentationModelAssemblerSupport<Empresa, EmpresaApenasNomeModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MystoreLinks mystoreLinks;
	
	@Autowired
	private MystoreSecurity mystoreSecurity;
	
	public EmpresaApenasNomeModelAssembler() {
		super(EmpresaController.class, EmpresaApenasNomeModel.class);
	}
	
	@Override
	public EmpresaApenasNomeModel toModel(Empresa empresa) {
		EmpresaApenasNomeModel empresaModel = createModelWithId(
				empresa.getId(), empresa);
		
		modelMapper.map(empresa, empresaModel);
		
		if (mystoreSecurity.podeGerenciarEmpresas()) {
			empresaModel.add(mystoreLinks.linkToEmpresas("empresas"));
		}
		
		return empresaModel;
	}
	
	@Override
	public CollectionModel<EmpresaApenasNomeModel> toCollectionModel(Iterable<? extends Empresa> entities) {
		CollectionModel<EmpresaApenasNomeModel> collectionModel = super.toCollectionModel(entities);
		
		if (mystoreSecurity.podeGerenciarEmpresas()) {
			collectionModel.add(mystoreLinks.linkToEmpresas());
		}
				
		return collectionModel;
	}
	
}
