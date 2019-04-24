package com.kec.comercial.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.kec.comercial.model.StatusPedido;

public class ResumoPedido{
	
	private Long id;
	private LocalDate dataCriacao;
	private BigDecimal valorTotal;
	private StatusPedido status;
	
	public ResumoPedido(Long id,  LocalDate dataCriacao, BigDecimal valorTotal, StatusPedido status) {
		this.id = id;
		this.dataCriacao = dataCriacao;
		this.valorTotal = valorTotal;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	

	

}
