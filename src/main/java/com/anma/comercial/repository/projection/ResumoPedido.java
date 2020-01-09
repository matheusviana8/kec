package com.anma.comercial.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.anma.comercial.model.StatusPedido;

public class ResumoPedido{
	
	private Long id;
	private String cliente;
	private LocalDate dataCriacao;
	private BigDecimal valorTotal;
	private StatusPedido status;
	
	public ResumoPedido(Long id, String cliente, LocalDate dataCriacao, BigDecimal valorTotal, StatusPedido status) {
		this.id = id;
		this.cliente = cliente;
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
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
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
