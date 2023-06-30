package com.inventario.back.app.domain.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Equipo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEquipo;

	@Column(nullable = false)
	private String numeroSerial;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String descripcion;

	@Column(columnDefinition = "NUMERIC DEFAULT 1")
	private int estado;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	private Date fechaCompra;

	@Column(nullable = false)
	private double valorCompra;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaActualiza;

	@Column(nullable = false)
	private double valorDepreciacion;
}
