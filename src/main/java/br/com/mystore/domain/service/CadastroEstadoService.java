package br.com.mystore.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mystore.domain.exception.EntidadeEmUsoException;
import br.com.mystore.domain.exception.EstadoNaoEncontradoException;
import br.com.mystore.domain.model.Estado;
import br.com.mystore.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	private static final String MSG_ESTADO_EM_USO 
		= "Estado de código %d não pode ser removido, pois está em uso";
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Transactional
	public Estado salvar(Estado estado) {
		estado.setVersao(estadoRepository.findByUltimaVersao() + 1);
		return estadoRepository.save(estado);
	}
	
	@Transactional
	public void excluir(Long estadoId) {
		try {
			estadoRepository.deleteById(estadoId);
			estadoRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(estadoId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_ESTADO_EM_USO, estadoId));
		}
	}

	public Estado buscarOuFalhar(Long estadoId) {
		return estadoRepository.findById(estadoId)
			.orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
	}
	
}
