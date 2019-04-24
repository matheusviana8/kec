package com.kec.comercial.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoCliente {
	FINAL("FINAL"),
	REVENDA("REVENDA"),
	DISTRIBUICAO("DISTRIBUIÇÃO"),
	FORNECEDOR("FORNECEDOR");
	
	private String descricao;
   
	TipoCliente(String descricao) {
        this.descricao = descricao;
    }
   
    public String getDescricao() {
        return descricao;
    }

}
