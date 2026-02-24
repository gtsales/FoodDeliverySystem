package dev.luiz.customer.system.dtos;

import java.io.Serializable;

import lombok.ToString;

@ToString
public class CepVerificationDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String cep;
	private String logradouro;
	private String complemento;
	private String unidade;
	private String bairro;
	private String localidade;
	private String uf;
	private String estado;
	
	public CepVerificationDto(String cep, String logradouro, String complemento, String unidade, String bairro, String localidade, String uf, String estado) {
		
		this.cep = cep;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.unidade = unidade;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.estado = estado;
	}
	
	public String getCep() {
		
		return cep;
	}
	
	public void setCep(String cep) {
		
		this.cep = cep;
	}
	
	public String getLogradouro() {
		
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		
		return complemento;
	}
	
	public void setComplemento(String complemento) {
		
		this.complemento = complemento;
	}
	
	public String getUnidade() {
		
		return unidade;
	}
	
	public void setUnidade(String unidade) {
		
		this.unidade = unidade;
	}
	
	public String getBairro() {
		
		return bairro;
	}
	
	public void setBairro(String bairro) {
		
		this.bairro = bairro;
	}
	
	public String getLocalidade() {
		
		return localidade;
	}
	
	public void setLocalidade(String localidade) {
		
		this.localidade = localidade;
	}
	
	public String getUf() {
		
		return uf;
	}
	
	public void setUf(String uf) {
		
		this.uf = uf;
	}
	
	public String getEstado() {
		
		return estado;
	}
	
	public void setEstado(String estado) {
		
		this.estado = estado;
	}
}
