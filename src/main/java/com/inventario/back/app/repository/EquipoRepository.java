package com.inventario.back.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventario.back.app.domain.models.Equipo;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer> {

}
