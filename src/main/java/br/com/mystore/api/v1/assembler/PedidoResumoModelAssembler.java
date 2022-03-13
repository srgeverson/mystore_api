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
	private MystoreLinks mystoreLinks;
	
	@Autowired
	private MystoreSecurity mystoreSecurity;

	public PedidoResumoModelAssembler() {
		super(PedidoController.class, PedidoResumoModel.class);
	}
	
	@Override
	public PedidoResumoModel toModel(Pedido pedido) {
		PedidoResumoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
		modelMapper.map(pedido, pedidoModel);
		
		if (mystoreSecurity.podePesquisarPedidos()) {
			pedidoModel.add(mystoreLinks.linkToPedidos("pedidos"));
		}
		
		if (mystoreSecurity.podeConsultarEmpresas()) {
			pedidoModel.getEmpresa().add(
					mystoreLinks.linkToEmpresa(pedido.getEmpresa().getId()));
		}

		if (mystoreSecurity.podeConsultarUsuariosGruposPermissoes()) {
			pedidoModel.getCliente().add(mystoreLinks.linkToUsuario(pedido.getCliente().getId()));
		}
		
		return pedidoModel;
	}

}
