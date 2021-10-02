package br.com.mystore.domain.event;

import br.com.mystore.domain.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CodigoValidarAcessoEvent {
	
	private Usuario usuario;

}
