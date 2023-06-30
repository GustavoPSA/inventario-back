package com.inventario.back.app.exception;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

	private String error;

	private String message;

	private Integer status;

	private List<String> errors;

	public ErrorResponse(String error, String message, Integer status) {
		super();
		this.error = error;
		this.message = message;
		this.status = status;
	}

	public ErrorResponse(String error, List<String> errors, Integer status) {
		super();
		this.error = error;
		this.errors = errors;
		this.status = status;
	}

	public ErrorResponse(String reasonPhrase, String cadenaErrors, int value, List<String> errors2) {
		// TODO Auto-generated constructor stub
	}

}
