package com.inventario.back.app.domain.dto;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipoEdicionDTO {

	private int idEquipo;

	@NotNull(message = "Numero Serial es un campo requerido")
	private String numeroSerial;

	@NotNull(message = "Nombre es un campo requerido")
	private String nombre;

	@NotNull(message = "Descripcion es un campo requerido")
	private String descripcion;

	private int estado;

	@NotNull(message = "Fecha Compra es un campo requerido")
	private Date fechaCompra;

	private Date fechaActualiza;
}
