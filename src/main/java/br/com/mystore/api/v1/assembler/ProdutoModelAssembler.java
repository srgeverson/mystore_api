package br.com.mystore.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.mystore.api.v1.MystoreLinks;
import br.com.mystore.api.v1.controller.EmpresaProdutoController;
import br.com.mystore.api.v1.model.ProdutoModel;
import br.com.mystore.core.security.MystoreSecurity;
import br.com.mystore.domain.model.Produto;

@Component
public class ProdutoModelAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MystoreLinks mystoreLinks;

	@Autowired
	private MystoreSecurity mystoreSecurity;

	public ProdutoModelAssembler() {
		super(EmpresaProdutoController.class, ProdutoModel.class);
	}

	@Override
	public ProdutoModel toModel(Produto produto) {
		ProdutoModel produtoModel = createModelWithId(produto.getId(), produto, produto.getEmpresa().getId());

		modelMapper.map(produto, produtoModel);

		// Quem pode consultar empresas, também pode consultar os produtos e fotos
		if (mystoreSecurity.podeConsultarEmpresas()) {
			produtoModel.add(mystoreLinks.linkToProdutos(produto.getEmpresa().getId(), "produtos"));

			// produtoModel.add(mystoreLinks.linkToFotoProduto(produto.getEmpresa().getId(),
			// produto.getId(), "foto"));
		}

		return produtoModel;
	}

}
