package br.com.ultracar.treinamento.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@SuppressWarnings("serial")
@Table(name = "tb_bairro")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Bairro implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bairro_sequence")
	@SequenceGenerator(name = "bairro_sequence", sequenceName = "bairro_id_sequence", allocationSize = 1)
	@Column(name = "id_bairro", nullable = false)
	private Long id;
	
	@Size(min = 5, max = 100)
	@Column(name = "ds_nome", length = 100, nullable = false)
	private String nome;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cidade", foreignKey = @ForeignKey(name = "fk_bairro_cidade"), nullable = false)
	private Cidade cidade;
	
	@JsonBackReference
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "bairro", cascade = CascadeType.ALL)
	private Set<Endereco> endereco = new HashSet<>();

	public Bairro() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Set<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(Set<Endereco> endereco) {
		this.endereco = endereco;
	}

}
