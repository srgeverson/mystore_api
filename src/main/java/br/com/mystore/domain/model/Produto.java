package br.com.mystore.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "produtos")
public class Produto {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = true)
	private String descricao;

	@Column(name = "preco_compra", nullable = true)
	private BigDecimal compra;

	@Column(name = "preco_venda", nullable = true)
	private BigDecimal venda;

	@Column(name = "preco_custo", nullable = true)
	private BigDecimal custo;

	@Column(nullable = false)
	private Boolean ativo;

	@ManyToOne()
	@JoinColumn(name = "empresas_id", nullable = false)
	private Empresa empresa;

}