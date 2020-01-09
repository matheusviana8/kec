package com.anma.comercial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anma.comercial.model.Grupo;
import com.anma.comercial.repository.grupo.GrupoRepositoryQuery;

public interface GrupoRepository extends JpaRepository<Grupo, Long>, GrupoRepositoryQuery {

}
