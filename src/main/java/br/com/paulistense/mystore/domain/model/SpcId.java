package br.com.paulistense.mystore.domain.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class SpcId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long cdemp;
	private Long numpc;
	
}
