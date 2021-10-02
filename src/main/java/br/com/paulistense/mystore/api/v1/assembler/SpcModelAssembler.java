package br.com.paulistense.mystore.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.paulistense.mystore.api.v1.OlhodogestorLinks;
import br.com.paulistense.mystore.api.v1.model.SpcModel;
import br.com.paulistense.mystore.domain.model.Spc;

@Component
public class SpcModelAssembler extends RepresentationModelAssemblerSupport<Spc, SpcModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private OlhodogestorLinks olhodogestorLinks;

	public SpcModelAssembler() {
		super(Spc.class, SpcModel.class);
	}

	@Override
	public SpcModel toModel(Spc spc) {
		var ids = String.format("?cdemp=%s&numpc=%s", spc.getId().getCdemp(), spc.getId().getNumpc());
		SpcModel spcModel = createModelWithId(ids, spc);

		modelMapper.map(spc, spcModel);

		spcModel.add(olhodogestorLinks.linkToSpcs("pedidos-vendas"));

		return spcModel;
	}

	@Override
	public CollectionModel<SpcModel> toCollectionModel(Iterable<? extends Spc> entities) {
		return super.toCollectionModel(entities).add(olhodogestorLinks.linkToGraficos());
	}

}
