package com.kec.comercial.model;


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
	public static volatile SingularAttribute<Pedido, Cliente> cliente;
	public static volatile SingularAttribute<Pedido, StatusPedido> status;
	public static volatile SingularAttribute<Pedido, BigDecimal> valorTotal;

}
