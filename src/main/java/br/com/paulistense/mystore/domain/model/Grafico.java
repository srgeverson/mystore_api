<<<<<<< HEAD:src/main/java/br/com/mystore/domain/model/Permissao.java
package br.com.mystore.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "permissoes")
public class Permissao {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String descricao;

}
=======
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
>>>>>>> edccf1f8998c63d51a5dd60c58ad4c4cafdec7b0:src/main/java/br/com/paulistense/mystore/domain/model/Grafico.java
