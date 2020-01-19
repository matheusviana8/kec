package com.anma.comercial.repository.categoria;

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

import com.anma.comercial.model.Categoria;
import com.anma.comercial.model.Categoria_;
import com.anma.comercial.repository.filter.CategoriaFilter;

public class CategoriaRepositoryImpl implements CategoriaRepositoryQuery{
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Categoria> filtrar(CategoriaFilter categoriaFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Categoria> criteria = builder.createQuery(Categoria.class);
		Root<Categoria> root = criteria.from(Categoria.class);
		
		Predicate[] predicates = criarRestricoes(categoriaFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Categoria> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query,pageable);
		
	    return new PageImpl<>(query.getResultList(), pageable, total(categoriaFilter));
	}
	
	private Predicate[] criarRestricoes(CategoriaFilter categoriaFilter, CriteriaBuilder builder,
			Root<Categoria> root) {
		List<Predicate> predicates = new ArrayList<>();
			
		if (!StringUtils.isEmpty(categoriaFilter.getId())) {
			predicates.add(builder.equal(root.get(Categoria_.id), categoriaFilter.getId()));
		}
		
		if (!StringUtils.isEmpty(categoriaFilter.getDescricao())) {
			predicates.add(builder.like(
					builder.lower(root.get(Categoria_.nome)), "%" + categoriaFilter.getDescricao().toLowerCase() + "%"));
		}
		if (!StringUtils.isEmpty(categoriaFilter.getTipo())) {
			predicates.add(builder.equal(root.get(Categoria_.tipo), categoriaFilter.getTipo()));
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
	
	private Long total(CategoriaFilter categoriaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Categoria> root = criteria.from(Categoria.class);
		
		Predicate[] predicates = criarRestricoes(categoriaFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}


}
