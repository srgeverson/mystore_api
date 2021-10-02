package br.com.mystore.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mystore.domain.exception.EmpresaNaoEncontradaException;
import br.com.mystore.domain.model.Cidade;
import br.com.mystore.domain.model.Empresa;
import br.com.mystore.domain.model.FormaPagamento;
import br.com.mystore.domain.model.Usuario;
import br.com.mystore.domain.repository.EmpresaRepository;

@Service
public class CadastroEmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamento;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuario;
	
	@Transactional
	public Empresa salvar(Empresa empresa) {
		Long cidadeId = empresa.getEndereco().getCidade().getId();

		Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);
		
		empresa.getEndereco().setCidade(cidade);
		
		return empresaRepository.save(empresa);
	}
	
	@Transactional
	public void ativar(Long empresaId) {
		Empresa empresaAtual = buscarOuFalhar(empresaId);
		
		empresaAtual.ativar();
	}
	
	@Transactional
	public void inativar(Long empresaId) {
		Empresa empresaAtual = buscarOuFalhar(empresaId);
		
		empresaAtual.inativar();
	}
	
	@Transactional
	public void ativar(List<Long> empresaIds) {
		empresaIds.forEach(this::ativar);
	}
	
	@Transactional
	public void inativar(List<Long> empresaIds) {
		empresaIds.forEach(this::inativar);
	}
	
	@Transactional
	public void desassociarFormaPagamento(Long empresaId, Long formaPagamentoId) {
		Empresa empresa = buscarOuFalhar(empresaId);
		FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);
		
		empresa.removerFormaPagamento(formaPagamento);
	}
	
	@Transactional
	public void associarFormaPagamento(Long empresaId, Long formaPagamentoId) {
		Empresa empresa = buscarOuFalhar(empresaId);
		FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);
		
		empresa.adicionarFormaPagamento(formaPagamento);
	}
	
	@Transactional
	public void desassociarResponsavel(Long empresaId, Long usuarioId) {
		Empresa empresa = buscarOuFalhar(empresaId);
		Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);
		
		empresa.removerResponsavel(usuario);
	}
	
	@Transactional
	public void associarResponsavel(Long empresaId, Long usuarioId) {
		Empresa empresa = buscarOuFalhar(empresaId);
		Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);
		
		empresa.adicionarResponsavel(usuario);
	}
	
	public Empresa buscarOuFalhar(Long empresaId) {
		return empresaRepository.findById(empresaId)
			.orElseThrow(() -> new EmpresaNaoEncontradaException(empresaId));
	}
	
}
