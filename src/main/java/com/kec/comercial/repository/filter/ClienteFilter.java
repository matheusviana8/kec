package com.kec.comercial.repository.filter;

import com.kec.comercial.model.TipoCliente;

public class ClienteFilter {
	
	private Long id;
	private String nome;
	private TipoCliente tipo;
	
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
	public TipoCliente getTipo() {
		return tipo;
	}
	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo;
	}


	
	
	

}
