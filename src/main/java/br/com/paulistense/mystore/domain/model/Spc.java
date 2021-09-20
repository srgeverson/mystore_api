package br.com.paulistense.mystore.domain.model;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "SPC")
public class Spc {

	@EmbeddedId	
	private SpcId id;
	
	@Column(name = "aprovcrd")
	private Long aprovaCredito;
	
}
