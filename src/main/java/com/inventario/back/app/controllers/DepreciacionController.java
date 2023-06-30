package com.inventario.back.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventario.back.app.domain.dto.DepreciacionDTO;
import com.inventario.back.app.exception.ErrorResponse;
import com.inventario.back.app.service.IDepreciacionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Depreciacion", description = "Gestion de depreciaciones")
@RestController
@RequestMapping("/depreciacion")
public class DepreciacionController {

	@Autowired
	private IDepreciacionService service;

	@Operation(summary = "Lista todas las Depreciaciones")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Listado de Depreciaciones", content = @Content(schema = @Schema(implementation = DepreciacionDTO.class))),
			@ApiResponse(responseCode = "404", description = "No se encontraton Depreciaciones", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "400", description = "Peticion mal formada", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error inesperado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
	@GetMapping
	public ResponseEntity<List<DepreciacionDTO>> listar() {

		return ResponseEntity.ok(service.list());
	}

	@Operation(summary = "Obtiene una Depreciacion determinada")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Existe la Depreciacion", content = @Content(schema = @Schema(implementation = DepreciacionDTO.class))),
			@ApiResponse(responseCode = "404", description = "La Depreciacion no existe", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "400", description = "Peticion mal formada", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error inesperado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
	@GetMapping("/{idDepreciacion}")
	public ResponseEntity<DepreciacionDTO> consultarId(@PathVariable("idDepreciacion") int idDepreciacion) {

		return ResponseEntity.ok(service.getById(idDepreciacion));
	}

	@Operation(summary = "Registra una nueva Depreciacion")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Se registra la Depreciacion", content = @Content(schema = @Schema(implementation = DepreciacionDTO.class))),
			@ApiResponse(responseCode = "400", description = "Peticion mal formada", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error inesperado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
	@PostMapping
	public ResponseEntity<DepreciacionDTO> agregar(@RequestBody DepreciacionDTO depreciacionDto) {

		return ResponseEntity.ok(service.add(depreciacionDto));
	}

}
