package br.com.ultracar.treinamento.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@SuppressWarnings("serial")
@Table(name = "tb_grupo_acesso")
public class GrupoAcesso implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grupo_acesso_sequence")
	@SequenceGenerator(name = "grupo_acesso_sequence", sequenceName = "grupo_acesso_id_sequence", allocationSize = 1)
	@Column(name = "id_grupo_acesso", nullable = false)
	private Long id;
	
	@Size(min = 5, max = 45)
	@Column(name = "ds_descricao", length = 45, nullable = false)
	private String descricao;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "fk_grupo_acesso_usuario"), nullable = false)
	@JsonBackReference
	private Usuario usuario;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grupoAcesso")
	@JsonManagedReference
	private Set<PermissaoAcesso> permissoesDeAcesso = new HashSet<>();

	public GrupoAcesso() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<PermissaoAcesso> getPermissoesDeAcesso() {
		return permissoesDeAcesso;
	}

	public void setPermissoesDeAcesso(Set<PermissaoAcesso> permissoesDeAcesso) {
		this.permissoesDeAcesso = permissoesDeAcesso;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
