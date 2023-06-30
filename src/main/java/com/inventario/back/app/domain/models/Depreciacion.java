package com.inventario.back.app.domain.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Depreciacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDepreciacion;

	@ManyToOne
	@JoinColumn(name = "idEquipo")
	private Equipo equipo;

	@Column(nullable = false)
	private int ano;

	@Column(nullable = false)
	private double porPerdida;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(columnDefinition = "DATE DEFAULT CURRENT_DATE")
	private Date fechaCreacion;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaActualiza;

}
