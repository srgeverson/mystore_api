package br.com.mystore.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.mystore.api.v1.MystoreLinks;
import br.com.mystore.api.v1.controller.PedidoController;
import br.com.mystore.api.v1.model.PedidoResumoModel;
import br.com.mystore.core.security.MystoreSecurity;
import br.com.mystore.domain.model.Pedido;

@Component
public class PedidoResumoModelAssembler 
		extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MystoreLinks algaLinks;
	
	@Autowired
	private MystoreSecurity algaSecurity;

	public PedidoResumoModelAssembler() {
		super(PedidoController.class, PedidoResumoModel.class);
	}
	
	@Override
	public PedidoResumoModel toModel(Pedido pedido) {
		PedidoResumoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
		modelMapper.map(pedido, pedidoModel);
		
		if (algaSecurity.podePesquisarPedidos()) {
			pedidoModel.add(algaLinks.linkToPedidos("pedidos"));
		}
		
		if (algaSecurity.podeConsultarRestaurantes()) {
			pedidoModel.getRestaurante().add(
					algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));
		}

		if (algaSecurity.podeConsultarUsuariosGruposPermissoes()) {
			pedidoModel.getCliente().add(algaLinks.linkToUsuario(pedido.getCliente().getId()));
		}
		
		return pedidoModel;
	}

}
