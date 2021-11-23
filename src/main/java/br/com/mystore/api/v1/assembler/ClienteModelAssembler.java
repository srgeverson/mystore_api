package br.com.mystore.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.mystore.api.v1.MystoreLinks;
import br.com.mystore.api.v1.controller.EmpresaClienteController;
import br.com.mystore.api.v1.model.ClienteModel;
import br.com.mystore.core.security.MystoreSecurity;
import br.com.mystore.domain.model.Cliente;

@Component
public class ClienteModelAssembler extends RepresentationModelAssemblerSupport<Cliente, ClienteModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MystoreLinks mystoreLinks;

	@Autowired
	private MystoreSecurity mystoreSecurity;

	public ClienteModelAssembler() {
		super(EmpresaClienteController.class, ClienteModel.class);
	}

	@Override
	public ClienteModel toModel(Cliente cliente) {
		ClienteModel clienteModel = createModelWithId(cliente.getId(), cliente, cliente.getEmpresa().getId());

		modelMapper.map(cliente, clienteModel);

		// Quem pode consultar empresas, também pode consultar os clientes e fotos
		if (mystoreSecurity.podeConsultarEmpresas()) {
			clienteModel.add(mystoreLinks.linkToClientes(cliente.getEmpresa().getId(), "clientes"));

			// clienteModel.add(mystoreLinks.linkToFotoCliente(cliente.getEmpresa().getId(),
			// cliente.getId(), "foto"));
		}

		return clienteModel;
	}

}
