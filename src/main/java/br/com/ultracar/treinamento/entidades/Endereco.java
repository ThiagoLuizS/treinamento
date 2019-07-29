package br.com.ultracar.treinamento.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.ultracar.treinamento.entidades.enumeradores.TipoLocal;

@Entity
@SuppressWarnings("serial")
@Table(name = "tb_endereco")
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
	
	@Column(name = "nm_numero")
	private Integer numero;
	
	@Column(name = "ds_complemento", length = 255)
	private String complemento;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "en_tipo_local", length = 10, nullable = false)
	private TipoLocal tipoLocal;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_bairro", foreignKey = @ForeignKey(name = "fk_endereco_bairro"), nullable = false)
	private Bairro bairro;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "endereco")
	private Set<Ponto> pontos = new HashSet<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "endereco")
	private Set<Servico> servicos = new HashSet<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "endereco")
	private Set<Solicitante> solicitantes = new HashSet<>();

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

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public TipoLocal getTipoLocal() {
		return tipoLocal;
	}

	public void setTipoLocal(TipoLocal tipoLocal) {
		this.tipoLocal = tipoLocal;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Set<Ponto> getPontos() {
		return pontos;
	}

	public void setPontos(Set<Ponto> pontos) {
		this.pontos = pontos;
	}

	public Set<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(Set<Servico> servicos) {
		this.servicos = servicos;
	}

	public Set<Solicitante> getSolicitantes() {
		return solicitantes;
	}

	public void setSolicitantes(Set<Solicitante> solicitantes) {
		this.solicitantes = solicitantes;
	}

	
	
}
