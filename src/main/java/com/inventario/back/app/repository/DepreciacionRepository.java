package com.inventario.back.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventario.back.app.domain.models.Depreciacion;
import com.inventario.back.app.domain.models.Equipo;

@Repository
public interface DepreciacionRepository extends JpaRepository<Depreciacion, Integer> {

	List<Depreciacion> findByEquipo(Equipo equipo);

}
