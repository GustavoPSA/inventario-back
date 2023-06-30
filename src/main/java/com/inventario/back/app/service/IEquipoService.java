package com.inventario.back.app.service;

import java.util.List;

import com.inventario.back.app.domain.dto.EquipoDTO;
import com.inventario.back.app.domain.dto.EquipoEdicionDTO;

public interface IEquipoService {

	public List<EquipoDTO> list();

	public EquipoDTO getById(int idEquipo);

	public EquipoDTO add(EquipoDTO equipoDTO);

	public EquipoEdicionDTO edit(EquipoEdicionDTO equipoEdicionDTO);
}
