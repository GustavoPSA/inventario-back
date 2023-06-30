package com.inventario.back.app.domain.mapper;

import org.mapstruct.Mapper;

import com.inventario.back.app.domain.dto.EquipoEdicionDTO;
import com.inventario.back.app.domain.models.Equipo;

@Mapper(componentModel = "spring")
public interface EquipoEdicionMapper {

	Equipo dtoToEntity(EquipoEdicionDTO equipoEdicionDTO);

	EquipoEdicionDTO entityToDto(Equipo equipo);

}
