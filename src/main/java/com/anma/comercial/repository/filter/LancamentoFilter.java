package com.anma.comercial.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.anma.comercial.model.TipoLancamento;

public class LancamentoFilter {

	private String descricao;
	
	private TipoLancamento tipo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataVencimentoDe;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataVencimentoAte;

	public String getDescricao() {
		return descricao;
	}

	public TipoLancamento getTipo() {
		return tipo;
	}


	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataVencimentoDe() {
		return dataVencimentoDe;
	}

	public void setDataVencimentoDe(LocalDate dataVencimentoDe) {
		this.dataVencimentoDe = dataVencimentoDe;
	}

	public LocalDate getDataVencimentoAte() {
		return dataVencimentoAte;
	}

	public void setDataVencimentoAte(LocalDate dataVencimentoAte) {
		this.dataVencimentoAte = dataVencimentoAte;
	}

}
