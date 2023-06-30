package com.inventario.back.app.domain.dto;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepreciacionDTO {

	private int idDepreciacion;

	private EquipoDTO equipo;

	@NotEmpty
	private int ano;

	@NotEmpty
	private double porPerdida;

	@NotEmpty
	private Date fechaCreacion;

	private Date fechaActualiza;

}
