package br.com.mystore.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.mystore.api.v1.MystoreLinks;
import br.com.mystore.api.v1.controller.RestauranteProdutoController;
import br.com.mystore.api.v1.model.ProdutoModel;
import br.com.mystore.core.security.MystoreSecurity;
import br.com.mystore.domain.model.Produto;

@Component
public class ProdutoModelAssembler 
		extends RepresentationModelAssemblerSupport<Produto, ProdutoModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MystoreLinks algaLinks;
	
	@Autowired
	private MystoreSecurity algaSecurity;
	
	public ProdutoModelAssembler() {
		super(RestauranteProdutoController.class, ProdutoModel.class);
	}
	
	@Override
	public ProdutoModel toModel(Produto produto) {
		ProdutoModel produtoModel = createModelWithId(
				produto.getId(), produto, produto.getRestaurante().getId());
		
		modelMapper.map(produto, produtoModel);
		
		// Quem pode consultar restaurantes, também pode consultar os produtos e fotos
		if (algaSecurity.podeConsultarRestaurantes()) {
			produtoModel.add(algaLinks.linkToProdutos(produto.getRestaurante().getId(), "produtos"));

			produtoModel.add(algaLinks.linkToFotoProduto(
					produto.getRestaurante().getId(), produto.getId(), "foto"));
		}
		
		return produtoModel;
	}
	
}
