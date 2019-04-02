package com.kec.comercial.model;

public enum TipoCliente {
	FINAL("FINAL"),
	REVENDA("REVENDA"),
	DISTRIBUICAO("DISTRIBUIÇÃO");
	
   private String descricao;
   
   TipoCliente(String descricao) {
        this.descricao = descricao;
    }
 
    public String getDescricao() {
        return descricao;
    }

}
