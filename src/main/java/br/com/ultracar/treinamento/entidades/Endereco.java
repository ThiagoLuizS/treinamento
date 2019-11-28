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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.ultracar.treinamento.entidades.enumeradores.TipoLocal;

@Entity
@SuppressWarnings("serial")
@Table(name = "tb_endereco")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Endereco  implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_sequence")
	@SequenceGenerator(name = "endereco_sequence", sequenceName = "endereco_id_sequence", allocationSize = 1)
	@Column(name = "id_endereco", nullable = false)
	private Long id;
	
	@NotBlank
	@Size(min = 5, max = 128)
	@Column(name = "ds_logradouro", length = 128, nullable = false)
	private String logradouro;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "en_tipo_local", length = 10, nullable = false)
	private TipoLocal tipoLocal;
	
	@NotNull
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "tb_endereco_bairro", joinColumns = {
			@JoinColumn(name = "id_endereco", foreignKey = @ForeignKey(name="fk_endereco_bairro"))
	},inverseJoinColumns = {
			@JoinColumn(name = "id_bairro", foreignKey = @ForeignKey(name="fk_bairro_endereco"))
	})
	private Set<Bairro> bairro = new HashSet<>();
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "endereco")
	private Set<Complemento> complemento = new HashSet<>();
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "endereco")
	private Set<Cep> cep = new HashSet<>();
	
	public Endereco() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public TipoLocal getTipoLocal() {
		return tipoLocal;
	}

	public void setTipoLocal(TipoLocal tipoLocal) {
		this.tipoLocal = tipoLocal;
	}

	public Set<Bairro> getBairro() {
		return bairro;
	}

	public void setBairro(Set<Bairro> bairro) {
		this.bairro = bairro;
	}

	public Set<Complemento> getComplemento() {
		return complemento;
	}

	public void setComplemento(Set<Complemento> complemento) {
		this.complemento = complemento;
	}

	public Set<Cep> getCep() {
		return cep;
	}

	public void setCep(Set<Cep> cep) {
		this.cep = cep;
	}
}
