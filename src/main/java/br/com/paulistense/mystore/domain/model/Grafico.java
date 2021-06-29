package br.com.paulistense.mystore.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Grafico {

	@EqualsAndHashCode.Include
	@Id
	private Long id;
	
	@Column(nullable = false)
	private String descricao;	

	@Column(nullable = false)
	private String tipo;

	@Column(nullable = false)
	private Integer largura;
	
	@Column(nullable = false)
	private Integer altura;	
}
