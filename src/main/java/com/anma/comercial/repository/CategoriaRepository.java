package com.anma.comercial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anma.comercial.model.Categoria;
import com.anma.comercial.repository.categoria.CategoriaRepositoryQuery;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>,CategoriaRepositoryQuery {


}
