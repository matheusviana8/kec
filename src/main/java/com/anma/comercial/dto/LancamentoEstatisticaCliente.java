package com.anma.comercial.dto;

import java.math.BigDecimal;

import com.anma.comercial.model.Cliente;
import com.anma.comercial.model.TipoLancamento;


public class LancamentoEstatisticaCliente {
	
	private TipoLancamento tipo;
	
	private Cliente cliente;
	
	private BigDecimal total;

	public LancamentoEstatisticaCliente(TipoLancamento tipo, Cliente cliente, BigDecimal total) {
		this.tipo = tipo;
		this.cliente = cliente;
		this.total = total;
	}

	public TipoLancamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
}