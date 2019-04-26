package com.kec.comercial.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class PedidoFilter {
	
	private Long id;
	private String cliente;
	    
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataCriacaoDe;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataCriacaoAte;

	public LocalDate getDataCriacaoDe() {
		return dataCriacaoDe;
	}

	public void setDataCriacaoDe(LocalDate dataCriacaoDe) {
		this.dataCriacaoDe = dataCriacaoDe;
	}

	public LocalDate getDataCriacaoAte() {
		return dataCriacaoAte;
	}

	public void setDataCriacaoAte(LocalDate dataCriacaoAte) {
		this.dataCriacaoAte = dataCriacaoAte;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
	
	

}
