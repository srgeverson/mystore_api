package br.com.mystore.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mystore.domain.exception.NegocioException;
import br.com.mystore.domain.exception.PedidoNaoEncontradoException;
import br.com.mystore.domain.model.Cliente;
import br.com.mystore.domain.model.Empresa;
import br.com.mystore.domain.model.FormaPagamento;
import br.com.mystore.domain.model.Pedido;
import br.com.mystore.domain.model.Produto;
import br.com.mystore.domain.repository.PedidoRepository;

@Service
public class EmissaoPedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private CadastroEmpresaService cadastroEmpresa;

	@Autowired
	private CadastroClienteService cadastroCliente;

	@Autowired
	private CadastroProdutoService cadastroProduto;

	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamento;

	@Transactional
	public Pedido emitir(Pedido pedido) {
		validarPedido(pedido);
		validarItens(pedido);

		//pedido.setTaxaFrete(pedido.getEmpresa().getTaxaFrete());
		pedido.calcularValorTotal();

		return pedidoRepository.save(pedido);
	}

	private void validarPedido(Pedido pedido) {
		//Cidade cidade = cadastroCidade.buscarOuFalhar(pedido.getEnderecoEntrega().getCidade().getId());
		//pedido.getEnderecoEntrega().setCidade(cidade);
		Cliente cliente = cadastroCliente.buscarOuFalhar(pedido.getEmpresa().getId(), pedido.getCliente().getId());
		pedido.setCliente(cliente);
		Empresa empresa = cadastroEmpresa.buscarOuFalhar(pedido.getEmpresa().getId());
		pedido.setEmpresa(empresa);
		FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(pedido.getFormaPagamento().getId());
		pedido.setFormaPagamento(formaPagamento);
		
		if (empresa.naoAceitaFormaPagamento(formaPagamento)) {
			throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse empresa.",
					formaPagamento.getDescricao()));
		}
	}

	private void validarItens(Pedido pedido) {
		pedido.getItens().forEach(item -> {
			Produto produto = cadastroProduto.buscarOuFalhar(
					pedido.getEmpresa().getId(), item.getProduto().getId());
			
			item.setPedido(pedido);
			item.setProduto(produto);
			//item.setPrecoUnitario(produto.getPreco());
		});
	}
	
	public Pedido buscarOuFalhar(String codigoPedido) {
		return pedidoRepository.findByCodigo(codigoPedido)
			.orElseThrow(() -> new PedidoNaoEncontradoException(codigoPedido));
	}

}
