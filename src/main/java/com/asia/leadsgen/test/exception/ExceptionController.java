package com.asia.leadsgen.test.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value = LoginException.class)
	public ResponseEntity<Object> handleLoginException(Exception e) {
		String stackTrace = ExceptionUtils.getStackTrace(e);
		logger.info(stackTrace);
		Map<String, String> response = new HashMap<>();
		response.put("message", "LOGIN REQUIRED");
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> handleException(Exception e) {
		String stackTrace = ExceptionUtils.getStackTrace(e);
		logger.info(stackTrace);
//        Map<String, String> response = new HashMap<>();
//        response.put("message", "INTERNAL SERVER ERROR");
		ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL SERVER ERROR");
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<Object> handleNotFoundException(Exception e) {
//        Map<String, String> response = new HashMap<>();
//        response.put("message", e.getMessage());
		ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = OracleException.class)
	public ResponseEntity<Object> handleOracleException(Exception e) {
		String stackTrace = ExceptionUtils.getStackTrace(e);
		logger.info(stackTrace);
//        Map<String, String> response = new HashMap<>();
//        response.put("message", e.getMessage());
		ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL SERVER ERROR");
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = MissingRequestHeaderException.class)
	public ResponseEntity<Object> handleMissingRequestHeaderException(MissingRequestHeaderException e) {
		String headerName = e.getHeaderName();
		ApiError apiError = new ApiError();

		if (headerName.equalsIgnoreCase("x-authorization")) {
			apiError.setStatus(HttpStatus.UNAUTHORIZED.value());
			apiError.setMessage("Login Required!");
			return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
		} else {
			apiError.setStatus(HttpStatus.BAD_REQUEST.value());
			apiError.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
		}
	}

	private Logger logger = Logger.getLogger(ExceptionController.class.getName());
}
