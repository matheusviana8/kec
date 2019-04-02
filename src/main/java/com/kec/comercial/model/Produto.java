package com.kec.comercial.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descricao;
	
	@Column(name = "valor_compra")
	private BigDecimal valorCompra;
	
	@Column(name = "valor_venda")
	private BigDecimal valorVenda;
	
	@Column(name = "valor_revenda")
	private BigDecimal valorRevenda;
	
	@Column(name = "valor_distribuicao")
	private BigDecimal valorDistribuicao;
	
	Integer saldo;
	
	Integer minimo;
	
	private String unidade;
	
	private Boolean ativo;
	
	@ManyToOne
	@JoinColumn(name = "id_fornecedor")
	Cliente fornecedor;
	
	@ManyToOne
	@JoinColumn(name = "id_grupo")
	Grupo grupo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = valorCompra;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}

	public BigDecimal getValorRevenda() {
		return valorRevenda;
	}

	public void setValorRevenda(BigDecimal valorRevenda) {
		this.valorRevenda = valorRevenda;
	}

	public BigDecimal getValorDistribuicao() {
		return valorDistribuicao;
	}

	public void setValorDistribuicao(BigDecimal valorDistribuicao) {
		this.valorDistribuicao = valorDistribuicao;
	}

	public Integer getSaldo() {
		return saldo;
	}

	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}

	public Integer getMinimo() {
		return minimo;
	}

	public void setMinimo(Integer minimo) {
		this.minimo = minimo;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}


	public Cliente getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Cliente fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
