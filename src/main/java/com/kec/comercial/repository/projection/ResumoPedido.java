package com.kec.comercial.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.kec.comercial.model.StatusPedido;

public class ResumoPedido{
	
	private Long id;
	private LocalDate dataCriacao;
	private BigDecimal valorTotal;
	private StatusPedido status;
	private String cliente;
	
	public ResumoPedido(Long id,  LocalDate dataCriacao, BigDecimal valorTotal, StatusPedido status, String cliente) {
		this.id = id;
		this.dataCriacao = dataCriacao;
		this.valorTotal = valorTotal;
		this.status = status;
		this.cliente = cliente;
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

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	

	

}
