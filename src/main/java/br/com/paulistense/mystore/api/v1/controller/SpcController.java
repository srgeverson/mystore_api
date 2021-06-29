package br.com.paulistense.mystore.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.paulistense.mystore.api.v1.assembler.SpcModelAssembler;
import br.com.paulistense.mystore.api.v1.model.SpcModel;
import br.com.paulistense.mystore.api.v1.openapi.controller.SpcControllerOpenApi;
import br.com.paulistense.mystore.domain.model.Spc;
import br.com.paulistense.mystore.domain.repository.SpcRepository;

@RestController
@RequestMapping(path = "/v1/spc", produces = MediaType.APPLICATION_JSON_VALUE)
public class SpcController implements SpcControllerOpenApi {

	@Autowired
	private SpcRepository spcRepository;

	@Autowired
	private SpcModelAssembler spcModelAssembler;
	
	@GetMapping
	public List<?> listar() {
		return spcRepository.findAll();
	}

	
	@Override
	@GetMapping("/carteira-clientes")
	public CollectionModel<SpcModel> carteiraDeClientes() {
		List<Spc> todosSpcs = spcRepository.findAll();
		return spcModelAssembler.toCollectionModel(todosSpcs);
	}
}
