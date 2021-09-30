package br.com.mystore.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import br.com.mystore.domain.event.CodigoValidarAcessoEvent;
import br.com.mystore.domain.model.Usuario;
import br.com.mystore.domain.service.EnvioEmailService;
import br.com.mystore.domain.service.EnvioEmailService.Mensagem;


@Component
public class NotificacaoCodigoAcessoListener {

	@Autowired
	private EnvioEmailService envioEmail;
	
	@TransactionalEventListener
	public void aoSolicitarCodigoAcesso(CodigoValidarAcessoEvent event) {
		Usuario usuario= event.getUsuario();
		
		var mensagem = Mensagem.builder()
				.assunto("MyStore - Usuário Cadastrado.")
				.corpo("emails/usuario-cadastrado.html")
				.variavel("usuario", usuario)
				.destinatario(usuario.getEmail())
				.build();

		envioEmail.enviar(mensagem);
	}
	
}	