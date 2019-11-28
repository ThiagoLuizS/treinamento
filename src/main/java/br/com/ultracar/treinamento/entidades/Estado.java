package br.com.ultracar.treinamento.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@SuppressWarnings("serial")
@Table(name = "tb_estado")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Estado implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estado_sequence")
	@SequenceGenerator(name = "estado_sequence", sequenceName = "estado_id_sequence", allocationSize = 1)
	@Column(name = "id_estado", nullable = false)
	private Long id;
	
	@NotBlank
	@Size(min = 2, max = 2)
	@Column(name = "ds_sigla", length = 2, nullable = false)
	private String sigla;
	
	public Estado() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}
