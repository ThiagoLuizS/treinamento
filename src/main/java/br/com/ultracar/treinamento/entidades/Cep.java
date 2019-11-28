package br.com.ultracar.treinamento.entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_cep")
@SuppressWarnings("serial")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cep implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cep_sequence")
	@SequenceGenerator(name = "cep_sequence", sequenceName = "cep_id_sequence", allocationSize = 1)
	@Column(name = "id_cep", nullable = false)
	private Long id;
	
	@Column(name = "nm_numero", nullable = false)
	private Long numero;
	
	@JoinColumn(name = "id_endereco")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Endereco endereco;

	public Cep() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
