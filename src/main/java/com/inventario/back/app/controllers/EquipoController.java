package com.inventario.back.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventario.back.app.domain.dto.EquipoDTO;
import com.inventario.back.app.domain.dto.EquipoEdicionDTO;
import com.inventario.back.app.exception.ErrorResponse;
import com.inventario.back.app.service.IEquipoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Equipos", description = "Gestion de Equipos")
@RestController
@RequestMapping("/equipo")
public class EquipoController {

	@Autowired
	private IEquipoService service;

	@Operation(summary = "Lista todos los Equipos")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Listado de Equipos", content = @Content(schema = @Schema(implementation = EquipoDTO.class))),
			@ApiResponse(responseCode = "404", description = "No se encontraton Equipos", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "400", description = "Peticion mal formada", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error inesperado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
	@GetMapping
	public ResponseEntity<List<EquipoDTO>> listar() {

		return ResponseEntity.ok(service.list());
	}

	@Operation(summary = "Obtiene un Equipo determinado")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Existe el Equipo", content = @Content(schema = @Schema(implementation = EquipoDTO.class))),
			@ApiResponse(responseCode = "404", description = "El Equipo no existe", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "400", description = "Peticion mal formada", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error inesperado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
	@GetMapping("/{idEquipo}")
	public ResponseEntity<EquipoDTO> consultarId(@PathVariable("idEquipo") int idEquipo) {

		return ResponseEntity.ok(service.getById(idEquipo));
	}

	@Operation(summary = "Registra un nuevo Equipo")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Se registra el Equipo", content = @Content(schema = @Schema(implementation = EquipoDTO.class))),
			@ApiResponse(responseCode = "400", description = "Peticion mal formada", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error inesperado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
	@PostMapping
	public ResponseEntity<EquipoDTO> agregar(@RequestBody @Valid EquipoDTO equipoDto) {

		return ResponseEntity.ok(service.add(equipoDto));
	}

	@Operation(summary = "Edita un nuevo Equipo")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Se edita el Equipo", content = @Content(schema = @Schema(implementation = EquipoEdicionDTO.class))),
			@ApiResponse(responseCode = "404", description = "El Equipo no existe", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "400", description = "Peticion mal formada", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error inesperado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
	@PutMapping
	public ResponseEntity<EquipoEdicionDTO> editar(@RequestBody @Valid EquipoEdicionDTO equipoEdicionDTO) {

		return ResponseEntity.ok(service.edit(equipoEdicionDTO));
	}
}
