package br.com.mystore.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "clientes")
public class Cliente {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String apelidoNomeFantazia;

	@Column(nullable = false)
	private String nomeRazaoSocial;

	@Column(nullable = false)
	private String cpfCnpj;

	@Column(nullable = true)
	private String email;

	@Column(nullable = true)
	private String telefone;
	
	@Column(nullable = true)
	private String celular;

	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;
	
	@Column(nullable = false)
	private Boolean ativo;

	@ManyToOne()
	@JoinColumn(name = "enderecos_id", nullable = false)
	private Endereco endereco;

	@ManyToOne()
	@JoinColumn(name = "empresas_id", nullable = false)
	private Empresa empresa;
}