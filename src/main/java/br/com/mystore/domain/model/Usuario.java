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

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import br.com.mystore.domain.event.CodigoValidarAcessoEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity(name = "usuarios")
public class Usuario extends AbstractAggregateRoot<Usuario> {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String email;

	@Column(nullable = true)
	private String senha;

	@Column(nullable = true)
	private Boolean ativo;

	@Column(nullable = true)
	private String codigoAcesso;

	@Column(nullable = true)
	private OffsetDateTime dataUltimoAcesso;

	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;

	@ManyToMany
	@JoinTable(name = "usuarios_grupos", joinColumns = @JoinColumn(name = "usuarios_id"), inverseJoinColumns = @JoinColumn(name = "grupos_id"))
	private Set<Grupo> grupos = new HashSet<>();

	public boolean adicionarGrupo(Grupo grupo) {
		return getGrupos().add(grupo);
	}

	public void ativar() {
		setAtivo(true);
	}

	public void enviarCodigoSolicitado() {
		registerEvent(new CodigoValidarAcessoEvent(this));
	}

	public void inativar() {
		setAtivo(false);
	}

	public boolean isNovo() {
		return getId() == null;
	}

	public boolean removerGrupo(Grupo grupo) {
		return getGrupos().remove(grupo);
	}

}
