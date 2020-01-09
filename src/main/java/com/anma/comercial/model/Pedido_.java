package com.anma.comercial.model;


import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pedido.class)
public abstract class Pedido_ {

	public static volatile SingularAttribute<Pedido, Long> id;
	public static volatile SingularAttribute<Pedido, LocalDate> dataCriacao;
	public static volatile SingularAttribute<Pedido, BigDecimal> valorTotal;
	public static volatile SingularAttribute<Pedido, StatusPedido> status;
	
	public static volatile SingularAttribute<Pedido, BigDecimal> valorFrete;
	public static volatile SingularAttribute<Pedido, String> vendedor;
	public static volatile SingularAttribute<Pedido, BigDecimal> valorDesconto;
	public static volatile SingularAttribute<Pedido, String> formaPagamento;
	public static volatile SingularAttribute<Pedido, String> observacao;
	public static volatile SingularAttribute<Pedido, Cliente> cliente;

}
	
