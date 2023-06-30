package com.inventario.back.app.controllers.interceptor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.inventario.back.app.exception.BackendException;
import com.inventario.back.app.exception.ErrorResponse;
import com.inventario.back.app.exception.NotFoundException;

@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(value = NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorResponse handleNotFoundException(NotFoundException ex) {
		// log.error(MESSAGE_LOG_ERROR, FAILED_DEPENDENCY.getReasonPhrase(),
		// ex.getClass().getSimpleName(),
		// request.getRequestURI(), ex.getMessage(), ex);

		return new ErrorResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage(), HttpStatus.NOT_FOUND.value());

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		List<String> errors = fieldErrors.stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
		String cadenaErrors = String.join(", ", errors);

		return new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), cadenaErrors, HttpStatus.BAD_REQUEST.value(),
				errors);
	}

	@ExceptionHandler({ Exception.class, BackendException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	protected ErrorResponse handleBackendException(Exception ex) {

		return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.value());

	}
}
