package com.anma.comercial.model;

public enum TipoCliente {
	
	FINAL("FINAL"),
	REVENDA("REVENDA"),
	DISTRIBUICAO("DISTRIBUIÇÃO"),
	FORNECEDOR("FORNECEDOR")
	
	;
	
	private String descricao;
   
	TipoCliente(String descricao) {
        this.descricao = descricao;
    }
   
    public String getDescricao() {
        return descricao;
    }

}
