package br.com.ultracar.treinamento.entidades.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CepDto {
	
	@Expose(serialize = true, deserialize = true)
	@SerializedName("resultado")
	private String resultado;
	
	@Expose(serialize = true, deserialize = true)
	@SerializedName("resultado_txt")
	private String resultadoTxt;
	
	@Expose(serialize = true, deserialize = true)
	@SerializedName("logradouro")
	private String logradouro;
	
	@Expose(serialize = true, deserialize = true)
	@SerializedName("tipo_logradouro")
	private String tipoLogradouro;
	
	@Expose(serialize = true, deserialize = true)
	@SerializedName("bairro")
	private String bairro;
	
	@Expose(serialize = true, deserialize = true)
	@SerializedName("cidade")
	private String cidade;
	
	@Expose(serialize = true, deserialize = true)
	@SerializedName("uf")
	private String uf;
	
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getResultadoTxt() {
		return resultadoTxt;
	}
	public void setResultadoTxt(String resultadoTxt) {
		this.resultadoTxt = resultadoTxt;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getTipoLogradouro() {
		return tipoLogradouro;
	}
	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
}
