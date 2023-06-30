package com.inventario.back.app.domain.dto;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipoDTO {

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

	@NotNull(message = "Valor compra es un campo requerido")
	private double valorCompra;

	private Date fechaActualiza;

	@NotNull(message = "Valor Depreciacion es un campo requerido")
	private double valorDepreciacion;

}
