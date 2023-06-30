package com.inventario.back.app.domain.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.inventario.back.app.domain.dto.EquipoDTO;
import com.inventario.back.app.domain.models.Equipo;

@Mapper(componentModel = "spring")
public interface EquipoMapper {

	Equipo dtoToEntity(EquipoDTO equipoDTO);

	EquipoDTO entityToDto(Equipo equipo);

	List<EquipoDTO> toListDTO(List<Equipo> equipos);
}
