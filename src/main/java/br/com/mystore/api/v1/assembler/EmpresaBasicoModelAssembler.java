package br.com.mystore.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.mystore.api.v1.MystoreLinks;
import br.com.mystore.api.v1.controller.EmpresaController;
import br.com.mystore.api.v1.model.EmpresaBasicoModel;
import br.com.mystore.core.security.MystoreSecurity;
import br.com.mystore.domain.model.Empresa;

@Component
public class EmpresaBasicoModelAssembler extends RepresentationModelAssemblerSupport<Empresa, EmpresaBasicoModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MystoreLinks mystoreLinks;

	@Autowired
	private MystoreSecurity mystoreSecurity;

	public EmpresaBasicoModelAssembler() {
		super(EmpresaController.class, EmpresaBasicoModel.class);
	}

	@Override
	public EmpresaBasicoModel toModel(Empresa empresa) {
		EmpresaBasicoModel empresaModel = createModelWithId(empresa.getId(), empresa);

		modelMapper.map(empresa, empresaModel);

		if (mystoreSecurity.podeGerenciarEmpresas(null)) {
			empresaModel.add(mystoreLinks.linkToEmpresas("empresas"));
		}

		return empresaModel;
	}

	@Override
	public CollectionModel<EmpresaBasicoModel> toCollectionModel(Iterable<? extends Empresa> entities) {
		CollectionModel<EmpresaBasicoModel> collectionModel = super.toCollectionModel(entities);

		if (mystoreSecurity.podeGerenciarEmpresas(null)) {
			collectionModel.add(mystoreLinks.linkToEmpresas());
		}

		return collectionModel;
	}

}
