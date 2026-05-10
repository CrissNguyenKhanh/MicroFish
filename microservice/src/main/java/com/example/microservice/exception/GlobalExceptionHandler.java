package com.example.microservice.exception;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<ApiErrorResponse> handleResponseStatusException(
			ResponseStatusException ex,
			HttpServletRequest request) {
		int statusCode = ex.getStatusCode().value();
		HttpStatus status = HttpStatus.resolve(statusCode);
		String error = status != null ? status.getReasonPhrase() : "Error";
		String message = ex.getReason() != null ? ex.getReason() : error;

		return ResponseEntity.status(statusCode).body(
				new ApiErrorResponse(
						Instant.now(),
						statusCode,
						error,
						message,
						request.getRequestURI(),
						Map.of()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiErrorResponse> handleValidationException(
			MethodArgumentNotValidException ex,
			HttpServletRequest request) {
		Map<String, String> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
				.collect(Collectors.toMap(
						FieldError::getField,
						fieldError -> {
							String message = fieldError.getDefaultMessage();
							return message != null ? message : "invalid value";
						},
						(existing, replacement) -> existing,
						LinkedHashMap::new));

		return ResponseEntity.badRequest().body(
				new ApiErrorResponse(
						Instant.now(),
						HttpStatus.BAD_REQUEST.value(),
						HttpStatus.BAD_REQUEST.getReasonPhrase(),
						"validation failed",
						request.getRequestURI(),
						fieldErrors));
	}
}
