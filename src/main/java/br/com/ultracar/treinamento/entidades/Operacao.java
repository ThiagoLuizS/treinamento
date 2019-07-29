package br.com.ultracar.treinamento.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.ultracar.treinamento.entidades.enumeradores.Crud;

@Entity
@SuppressWarnings("serial")
@Table(name = "tb_operacao")
public class Operacao implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operacao_sequence")
	@SequenceGenerator(name = "operacao_sequence", sequenceName = "operacao_id_sequence", allocationSize = 1)
	@Column(name = "id_operacao", nullable = false)
	private Long id;
	
	@Size(min = 5, max = 45)
	@Column(name = "ds_descricao", length = 45, nullable = false)
	private String descricao;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "en_operacao", length = 10, nullable = false)
	private Crud operacao;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "operacoes")
	private Set<PermissaoAcesso> permissoesDeAcesso = new HashSet<>();

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

	public Crud getOperacao() {
		return operacao;
	}

	public void setOperacao(Crud operacao) {
		this.operacao = operacao;
	}
	
}
