package com.anma.comercial.repository.filter;

import com.anma.comercial.model.TipoLancamento;

public class CategoriaFilter {
	
	private Long id;
	private String descricao;
	private TipoLancamento tipo;
	
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
	public TipoLancamento getTipo() {
		return tipo;
	}
	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}
	
	
	
	

}
