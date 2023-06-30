package com.inventario.back.app.service;

import java.util.List;

import com.inventario.back.app.domain.dto.DepreciacionDTO;

public interface IDepreciacionService {

	public List<DepreciacionDTO> list();

	public DepreciacionDTO getById(int idDepreciacion);

	public DepreciacionDTO add(DepreciacionDTO depreciacionDTO);
}
