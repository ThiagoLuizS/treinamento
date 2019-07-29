package br.com.ultracar.treinamento.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@SuppressWarnings("serial")
@Table(name = "tb_menu")
public class Menu implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_sequence")
	@SequenceGenerator(name = "menu_sequence", sequenceName = "menu_id_sequence", allocationSize = 1)
	@Column(name = "id_menu", nullable = false)
	private Long id;
	
	@Size(min = 5, max = 45)
	@Column(name = "ds_descricao", length = 45, nullable = false)
	private String descricao;
	
	@Size(min = 5, max = 150)
	@Column(name = "ds_url", length = 150, nullable = false)
	private String url;
	
	@Column(name = "ds_icone", length = 45)
	private String icone;
	
	@Column(name = "ds_indice", length = 45)
	private String indice;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_permissao_acesso", foreignKey = @ForeignKey(name = "fk_menu_permissao_acesso"), nullable = false)
	private PermissaoAcesso permissaoAcesso;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_menu_pai", foreignKey = @ForeignKey(name = "fk_menu_menu"))
	private Menu menu;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcone() {
		return icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}

	public String getIndice() {
		return indice;
	}

	public void setIndice(String indice) {
		this.indice = indice;
	}

	public PermissaoAcesso getPermissaoAcesso() {
		return permissaoAcesso;
	}

	public void setPermissaoAcesso(PermissaoAcesso permissaoAcesso) {
		this.permissaoAcesso = permissaoAcesso;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	
}
