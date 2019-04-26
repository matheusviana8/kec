package com.kec.comercial.repository.pedido;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.kec.comercial.model.Cliente_;
import com.kec.comercial.model.Pedido;
import com.kec.comercial.model.Pedido_;
import com.kec.comercial.repository.filter.PedidoFilter;
import com.kec.comercial.repository.projection.ResumoPedido;

public class PedidoRepositoryImpl implements PedidoRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Pedido> filtrar(PedidoFilter pedidoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
		Root<Pedido> root = criteria.from(Pedido.class);
		
		Predicate[] predicates = criarRestricoes(pedidoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Pedido> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query,pageable);
		
	    return new PageImpl<>(query.getResultList(), pageable, total(pedidoFilter));
	}

	@Override
	public Page<ResumoPedido> resumir(PedidoFilter pedidoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoPedido> criteria = builder.createQuery(ResumoPedido.class);
		Root<Pedido> root = criteria.from(Pedido.class);
		
		criteria.select(builder.construct(ResumoPedido.class
				, root.get(Pedido_.id)
				, root.get(Pedido_.cliente).get(Cliente_.nome)
				, root.get(Pedido_.dataCriacao)
				, root.get(Pedido_.valorTotal)
				, root.get(Pedido_.status)));
		
		Predicate[] predicates = criarRestricoes(pedidoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<ResumoPedido> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(pedidoFilter));
	}

	private Predicate[] criarRestricoes(PedidoFilter pedidoFilter, CriteriaBuilder builder,
			Root<Pedido> root) {
		List<Predicate> predicates = new ArrayList<>();
			
		if (!StringUtils.isEmpty(pedidoFilter.getId())) {
			predicates.add(builder.equal(root.get(Pedido_.id), pedidoFilter.getId()));
		}
		
		if (!StringUtils.isEmpty(pedidoFilter.getCliente())) {
			predicates.add(builder.like(
					builder.lower(root.get(Pedido_.cliente).get(Cliente_.nome)), "%" + pedidoFilter.getCliente().toLowerCase() + "%"));
		}
		
		if (pedidoFilter.getDataCriacaoDe() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get(Pedido_.dataCriacao), pedidoFilter.getDataCriacaoDe()));
		
		}
		if (pedidoFilter.getDataCriacaoAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(Pedido_.dataCriacao), pedidoFilter.getDataCriacaoAte()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	

	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long total(PedidoFilter pedidoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Pedido> root = criteria.from(Pedido.class);
		
		Predicate[] predicates = criarRestricoes(pedidoFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
