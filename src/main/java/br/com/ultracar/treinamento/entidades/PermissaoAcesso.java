package br.com.ultracar.treinamento.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ultracar.treinamento.entidades.enumeradores.Situacao;

@Entity
@SuppressWarnings("serial")
@Table(name = "tb_permissao_acesso")
public class PermissaoAcesso implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permissao_acesso_sequence")
	@SequenceGenerator(name = "permissao_acesso_sequence", sequenceName = "permissao_acesso_id_sequence", allocationSize = 1)
	@Column(name = "id_permissao_acesso", nullable = false)
	private Long id;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "en_situacao", length = 20, nullable = false)
	private Situacao situacao;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_grupo_acesso", foreignKey = @ForeignKey(name = "fk_permissao_acesso_grupo_acesso"), nullable = false)
	@JsonBackReference
	private GrupoAcesso grupoAcesso;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="tb_permissao_acesso_operacao", 
		foreignKey = @ForeignKey(name = "fk_operacao_permissao_acesso_operacao"),
    	joinColumns={@JoinColumn(name="id_operacao")},
    	inverseJoinColumns={@JoinColumn(name="id_permissao_acesso")},
    	inverseForeignKey = @ForeignKey(name = "fk_permissao_acesso_permissao_acesso_operacao")	)
	private Set<Operacao> operacoes = new HashSet<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "permissaoAcesso")
	private Set<Menu> menus = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public GrupoAcesso getGrupoAcesso() {
		return grupoAcesso;
	}

	public void setGrupoAcesso(GrupoAcesso grupoAcesso) {
		this.grupoAcesso = grupoAcesso;
	}

	public Set<Operacao> getOperacoes() {
		return operacoes;
	}

	public void setOperacoes(Set<Operacao> operacoes) {
		this.operacoes = operacoes;
	}

	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}
	
}
