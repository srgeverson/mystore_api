package br.com.mystore.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.mystore.api.v1.model.EnderecoModel;
import br.com.mystore.api.v1.model.input.ItemPedidoInput;
import br.com.mystore.domain.model.Endereco;
import br.com.mystore.domain.model.ItemPedido;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
		modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
		.addMappings(mapper -> mapper.skip(ItemPedido::setId));
	
	var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(
			Endereco.class, EnderecoModel.class);
	
	enderecoToEnderecoModelTypeMap.<String>addMapping(
			enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
			(enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));
	
	return modelMapper;
	}
	
}
