 	package br.com.mystore.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.mystore.core.validation.Groups;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "estados")
public class Estado {

	@NotNull(groups = Groups.EstadoId.class)
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	@NotBlank
	@Column(nullable = false)
	private String uf;

	@NotBlank
	@Column(name = "codigo_uf", nullable = false)
	private Long codigoUf;

	@NotBlank
	@Column(name = "regioes_id", nullable = false)
	private Long regiao;

	@Column(nullable = false)
	private Boolean ativo;
	
}