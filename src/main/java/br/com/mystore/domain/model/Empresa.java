package br.com.mystore.domain.model;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "empresas")
public class Empresa {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false, name = "cpf_cnpj")
	private String cpfCnpj;
	
	@ManyToOne
	@JoinColumn(name = "enderecos_id", nullable = false)
	private Endereco endereco;
	
	private Boolean ativo = Boolean.TRUE;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;
	
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataAtualizacao;
	
	@ManyToMany
	@JoinTable(name = "empresas_formas_pagamentos",
			joinColumns = @JoinColumn(name = "empresas_id"),
			inverseJoinColumns = @JoinColumn(name = "formas_pagamentos_id"))
	private Set<FormaPagamento> formasPagamento = new HashSet<>();
	
	@ManyToMany
	@JoinTable(name = "empresas_usuarios_responsaveis",
			joinColumns = @JoinColumn(name = "empresas_id"),
			inverseJoinColumns = @JoinColumn(name = "usuarios_id"))
	private Set<Usuario> responsaveis = new HashSet<>();
	
	//@OneToMany(mappedBy = "produto")
	//private List<Produto> produtos = new ArrayList<>();
	
	public void ativar() {
		setAtivo(true);
	}
	
	public void inativar() {
		setAtivo(false);
	}

	public boolean isInativo() {
		return !isAtivo();
	}

	public boolean isAtivo() {
		return this.ativo;
	}
	
	public boolean ativacaoPermitida() {
		return isInativo();
	}
	
	public boolean inativacaoPermitida() {
		return isAtivo();
	}
	
	public boolean removerFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamento().remove(formaPagamento);
	}
	
	public boolean adicionarFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamento().add(formaPagamento);
	}
	
	public boolean aceitaFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamento().contains(formaPagamento);
	}
	
	public boolean naoAceitaFormaPagamento(FormaPagamento formaPagamento) {
		return !aceitaFormaPagamento(formaPagamento);
	}
	
	public boolean removerResponsavel(Usuario usuario) {
		return getResponsaveis().remove(usuario);
	}
	
	public boolean adicionarResponsavel(Usuario usuario) {
		return getResponsaveis().add(usuario);
	}
	
}
