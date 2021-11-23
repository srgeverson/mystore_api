package br.com.mystore.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mystore.api.v1.MystoreLinks;
import br.com.mystore.api.v1.assembler.ClienteInputDisassembler;
import br.com.mystore.api.v1.assembler.ClienteModelAssembler;
import br.com.mystore.api.v1.model.ClienteModel;
import br.com.mystore.api.v1.model.imput.ClienteInput;
import br.com.mystore.api.v1.openapi.controller.EmpresaClienteControllerOpenApi;
import br.com.mystore.core.security.CheckSecurity;
import br.com.mystore.domain.model.Cliente;
import br.com.mystore.domain.model.Empresa;
import br.com.mystore.domain.repository.ClienteRepository;
import br.com.mystore.domain.service.CadastroClienteService;
import br.com.mystore.domain.service.CadastroEmpresaService;

@RestController
@RequestMapping(path = "/v1/empresas/{empresaId}/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmpresaClienteController implements EmpresaClienteControllerOpenApi {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private CadastroClienteService cadastroCliente;

	@Autowired
	private CadastroEmpresaService cadastroEmpresa;

	@Autowired
	private ClienteModelAssembler clienteModelAssembler;

	@Autowired
	private ClienteInputDisassembler clienteInputDisassembler;

	@Autowired
	private MystoreLinks mystoreLinks;

	@CheckSecurity.Empresas.PodeConsultar
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteModel adicionar(@PathVariable Long empresaId, @RequestBody @Valid ClienteInput clienteInput) {
		Empresa empresa = cadastroEmpresa.buscarOuFalhar(empresaId);

		Cliente cliente = clienteInputDisassembler.toDomainObject(clienteInput);
		cliente.setEmpresa(empresa);

		cliente = cadastroCliente.salvar(cliente);

		return clienteModelAssembler.toModel(cliente);
	}

	@CheckSecurity.Empresas.PodeConsultar
	@Override
	@PutMapping("/{clienteId}")
	public ClienteModel atualizar(@PathVariable Long empresaId, @PathVariable Long clienteId,
			@RequestBody @Valid ClienteInput clienteInput) {
		Cliente clienteAtual = cadastroCliente.buscarOuFalhar(empresaId, clienteId);

		clienteInputDisassembler.copyToDomainObject(clienteInput, clienteAtual);

		clienteAtual = cadastroCliente.salvar(clienteAtual);

		return clienteModelAssembler.toModel(clienteAtual);
	}

	@CheckSecurity.Empresas.PodeConsultar
	@Override
	@GetMapping("/{clienteId}")
	public ClienteModel buscar(@PathVariable Long empresaId, @PathVariable Long clienteId) {
		Cliente cliente = cadastroCliente.buscarOuFalhar(empresaId, clienteId);

		return clienteModelAssembler.toModel(cliente);
	}

	@CheckSecurity.Empresas.PodeConsultar
	@GetMapping
	@Override
	public CollectionModel<ClienteModel> listar(@PathVariable Long empresaId,
			@RequestParam(required = false, defaultValue = "false") Boolean incluirInativos) {
		Empresa empresa = cadastroEmpresa.buscarOuFalhar(empresaId);

		List<Cliente> todosClientes = null;

		if (incluirInativos) {
			todosClientes = clienteRepository.findTodosByEmpresa(empresa);
		} else {
			todosClientes = clienteRepository.findAtivosByEmpresa(empresa);
		}

		return clienteModelAssembler.toCollectionModel(todosClientes).add(mystoreLinks.linkToClientes(empresaId));
	}
}
