package com.kec.comercial.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PedidoEstatisticaDia {
	
	private LocalDate dia;
	
	private BigDecimal total;

	public PedidoEstatisticaDia(LocalDate dia, BigDecimal total) {
		this.dia = dia;
		this.total = total;
	}

	public LocalDate getDia() {
		return dia;
	}

	public void setDia(LocalDate dia) {
		this.dia = dia;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
		

}
