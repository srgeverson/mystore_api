package br.com.paulistense.mystore.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.paulistense.mystore.api.v1.OlhodogestorLinks;
import br.com.paulistense.mystore.api.v1.controller.GraficoController;
import br.com.paulistense.mystore.api.v1.model.GraficoModel;
import br.com.paulistense.mystore.domain.model.Grafico;

@Component
public class GraficoModelAssembler extends RepresentationModelAssemblerSupport<Grafico, GraficoModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private OlhodogestorLinks olhodogestorLinks;

	public GraficoModelAssembler() {
		super(GraficoController.class, GraficoModel.class);
	}

	@Override
	public GraficoModel toModel(Grafico Grafico) {
		GraficoModel GraficoModel = createModelWithId(Grafico.getId(), Grafico);

		modelMapper.map(Grafico, GraficoModel);

		GraficoModel.add(olhodogestorLinks.linkToGraficos("graficos"));

		return GraficoModel;
	}

	@Override
	public CollectionModel<GraficoModel> toCollectionModel(Iterable<? extends Grafico> entities) {
		return super.toCollectionModel(entities)
				.add(olhodogestorLinks.linkToGraficos());
	}

}
