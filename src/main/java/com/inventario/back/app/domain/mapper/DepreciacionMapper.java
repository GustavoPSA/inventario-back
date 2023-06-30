package com.inventario.back.app.domain.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.inventario.back.app.domain.dto.DepreciacionDTO;
import com.inventario.back.app.domain.models.Depreciacion;

@Mapper(componentModel = "spring")
public interface DepreciacionMapper {

	Depreciacion dtoToEntity(DepreciacionDTO depreciacionDTO);

	DepreciacionDTO entityToDto(Depreciacion depreciacion);

	List<DepreciacionDTO> toListDTO(List<Depreciacion> depreciaciones);
}
