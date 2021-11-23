package br.com.mystore.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.mystore.api.v1.MystoreLinks;
import br.com.mystore.api.v1.controller.PedidoController;
import br.com.mystore.api.v1.model.PedidoModel;
import br.com.mystore.core.security.MystoreSecurity;
import br.com.mystore.domain.model.Pedido;

@Component
public class PedidoModelAssembler 
		extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MystoreLinks mystoreLinks;
	
	@Autowired
	private MystoreSecurity mystoreSecurity;

	public PedidoModelAssembler() {
		super(PedidoController.class, PedidoModel.class);
	}
	
	@Override
	public PedidoModel toModel(Pedido pedido) {
		PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
		modelMapper.map(pedido, pedidoModel);
		
		// Não usei o método mystoreSecurity.podePesquisarPedidos(clienteId, restauranteId) aqui,
		// porque na geração do link, não temos o id do cliente e da empresa, 
		// então precisamos saber apenas se a requisição está autenticada e tem o escopo de leitura
		if (mystoreSecurity.podePesquisarPedidos()) {
			pedidoModel.add(mystoreLinks.linkToPedidos("pedidos"));
		}
		
		if (mystoreSecurity.podeGerenciarPedidos(pedido.getCodigo())) {
			if (pedido.podeSerConfirmado()) {
				pedidoModel.add(mystoreLinks.linkToConfirmacaoPedido(pedido.getCodigo(), "confirmar"));
			}
			
			if (pedido.podeSerCancelado()) {
				pedidoModel.add(mystoreLinks.linkToCancelamentoPedido(pedido.getCodigo(), "cancelar"));
			}
			
			if (pedido.podeSerEntregue()) {
				pedidoModel.add(mystoreLinks.linkToEntregaPedido(pedido.getCodigo(), "entregar"));
			}
		}
		
		if (mystoreSecurity.podeConsultarEmpresas()) {
			pedidoModel.getEmpresa().add(
					mystoreLinks.linkToEmpresa(pedido.getEmpresa().getId()));
		}
		
		if (mystoreSecurity.podeConsultarUsuariosGruposPermissoes()) {
			pedidoModel.getCliente().add(
					mystoreLinks.linkToUsuario(pedido.getCliente().getId()));
		}
		
		if (mystoreSecurity.podeConsultarFormasPagamento()) {
			pedidoModel.getFormaPagamento().add(
					mystoreLinks.linkToFormaPagamento(pedido.getFormaPagamento().getId()));
		}
		
		if (mystoreSecurity.podeConsultarCidades()) {
			pedidoModel.getEnderecoEntrega().getCidade().add(
					mystoreLinks.linkToCidade(pedido.getEnderecoEntrega().getCidade().getId()));
		}
		
		// Quem pode consultar restaurantes, também pode consultar os produtos dos restaurantes
		if (mystoreSecurity.podeConsultarEmpresas()) {
			pedidoModel.getItens().forEach(item -> {
				item.add(mystoreLinks.linkToProduto(
						pedidoModel.getEmpresa().getId(), item.getProdutoId(), "produto"));
			});
		}
		
		return pedidoModel;
	}

}
