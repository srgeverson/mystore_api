package br.com.paulistense.mystore.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.paulistense.mystore.api.v1.assembler.GraficoModelAssembler;
import br.com.paulistense.mystore.api.v1.model.GraficoModel;
import br.com.paulistense.mystore.api.v1.openapi.controller.GraficoControllerOpenApi;
import br.com.paulistense.mystore.domain.model.Grafico;
import br.com.paulistense.mystore.domain.repository.GraficoRepository;
import br.com.paulistense.mystore.domain.service.GraficoService;;

@RestController
@RequestMapping(path = "/v1/graficos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GraficoController implements GraficoControllerOpenApi {

	@Autowired
	private GraficoModelAssembler graficoModelAssembler;

	@Autowired
	private GraficoRepository graficoRepository;

	@Autowired
	private GraficoService graficoService;

	@GetMapping("/{graficoId}")
	public GraficoModel buscar(@PathVariable Long graficoId) {
		Grafico grafico = graficoService.buscarOuFalhar(graficoId);

		return graficoModelAssembler.toModel(grafico);
	}

	@Override
	@GetMapping
	public CollectionModel<GraficoModel> listar() {
		List<Grafico> todosGraficos = graficoRepository.findAll();

		return graficoModelAssembler.toCollectionModel(todosGraficos);
	}
}
