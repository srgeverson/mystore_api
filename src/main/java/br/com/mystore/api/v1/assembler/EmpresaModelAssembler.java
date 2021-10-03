package br.com.mystore.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.mystore.api.v1.MystoreLinks;
import br.com.mystore.api.v1.controller.EmpresaController;
import br.com.mystore.api.v1.model.EmpresaModel;
import br.com.mystore.core.security.MystoreSecurity;
import br.com.mystore.domain.model.Empresa;

@Component
public class EmpresaModelAssembler extends RepresentationModelAssemblerSupport<Empresa, EmpresaModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MystoreLinks mystoreLinks;

	@Autowired
	private MystoreSecurity mystoreSecurity;

	public EmpresaModelAssembler() {
		super(EmpresaController.class, EmpresaModel.class);
	}

	@Override
	public EmpresaModel toModel(Empresa empresa) {
		EmpresaModel empresaModel = createModelWithId(empresa.getId(), empresa);
		modelMapper.map(empresa, empresaModel);

		if (mystoreSecurity.podeGerenciarEmpresas(null)) {
			empresaModel.add(mystoreLinks.linkToEmpresas("empresas"));
		}

		if (mystoreSecurity.podeGerenciarEmpresas(null)) {
			if (empresa.ativacaoPermitida()) {
				empresaModel.add(mystoreLinks.linkToEmpresaAtivacao(empresa.getId(), "ativar"));
			}

			if (empresa.inativacaoPermitida()) {
				empresaModel.add(mystoreLinks.linkToEmpresaInativacao(empresa.getId(), "inativar"));
			}
		}

		if (mystoreSecurity.podeGerenciarEmpresas(empresa.getId())) {
			empresaModel.add(mystoreLinks.linkToProdutos(empresa.getId(), "produtos"));
		}

		if (mystoreSecurity.podeConsultarCidades()) {
			if (empresaModel.getEndereco() != null && empresaModel.getEndereco().getCidade() != null) {
				empresaModel.getEndereco().getCidade()
						.add(mystoreLinks.linkToCidade(empresa.getEndereco().getCidade().getId()));
			}
		}

		if (mystoreSecurity.podeGerenciarEmpresas(empresa.getId())) {
			empresaModel.add(mystoreLinks.linkToEmpresaFormasPagamento(empresa.getId(), "formas-pagamento"));
		}

		if (mystoreSecurity.podeGerenciarEmpresas(empresa.getId())) {
			// empresaModel.add(mystoreLinks.linkToEmpresaResponsaveis(empresa.getId(),
			// "responsaveis"));
		}

		return empresaModel;
	}

	@Override
	public CollectionModel<EmpresaModel> toCollectionModel(Iterable<? extends Empresa> entities) {
		CollectionModel<EmpresaModel> collectionModel = super.toCollectionModel(entities);

		if (mystoreSecurity.podeGerenciarEmpresas(null)) {
			collectionModel.add(mystoreLinks.linkToEmpresas());
		}

		return collectionModel;
	}

}
