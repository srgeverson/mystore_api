package br.com.mystore.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.mystore.api.v1.MystoreLinks;
import br.com.mystore.api.v1.controller.FormaPagamentoController;
import br.com.mystore.api.v1.model.FormaPagamentoModel;
import br.com.mystore.core.security.MystoreSecurity;
import br.com.mystore.domain.model.FormaPagamento;

@Component
public class FormaPagamentoModelAssembler 
		extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MystoreLinks mystoreLinks;
	
	@Autowired
	private MystoreSecurity mystoreSecurity;
	
	public FormaPagamentoModelAssembler() {
		super(FormaPagamentoController.class, FormaPagamentoModel.class);
	}
	
	@Override
	public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
		FormaPagamentoModel formaPagamentoModel = 
				createModelWithId(formaPagamento.getId(), formaPagamento);
		
		modelMapper.map(formaPagamento, formaPagamentoModel);
		
		if (mystoreSecurity.podeConsultarFormasPagamento()) {
			formaPagamentoModel.add(mystoreLinks.linkToFormasPagamento("formasPagamento"));
		}
		
		return formaPagamentoModel;
	}
	
	@Override
	public CollectionModel<FormaPagamentoModel> toCollectionModel(Iterable<? extends FormaPagamento> entities) {
		CollectionModel<FormaPagamentoModel> collectionModel = super.toCollectionModel(entities);
		
		if (mystoreSecurity.podeConsultarFormasPagamento()) {
			collectionModel.add(mystoreLinks.linkToFormasPagamento());
		}
			
		return collectionModel;
	}
	
}
