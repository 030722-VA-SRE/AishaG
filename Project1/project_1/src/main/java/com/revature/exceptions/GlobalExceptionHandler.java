package com.revature.exceptions;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import com.revature.models.ApiError;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler({UserFoundException.class, UserNotFoundException.class, UserFoundException.class, ItemNotFoundException.class, AuthenticationException.class, AuthorizationException.class})
	public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
	
		if (ex instanceof UserNotFoundException) {
			HttpStatus status = HttpStatus.NOT_FOUND;
			UserNotFoundException unfe = (UserNotFoundException) ex;
			LOG.trace("UserNotFoundException called");
			return handleUserNotFoundException(unfe, headers, status, request);
		} else if (ex instanceof UserFoundException) {
			HttpStatus status = HttpStatus.BAD_REQUEST;
			UserFoundException ufe = (UserFoundException) ex;
			LOG.trace("UserFoundException called");
			return handleUserFoundException(ufe, headers, status, request);
		} else if (ex instanceof ItemNotFoundException){
			HttpStatus status = HttpStatus.NOT_FOUND;
			ItemNotFoundException infe = (ItemNotFoundException) ex;
			LOG.trace("ItemNotFoundException called");
			return handleItemNotFoundException(infe, headers, status, request);
		} else if (ex instanceof AuthorizationException) {
			HttpStatus status = HttpStatus.BAD_REQUEST;
			AuthorizationException ae = (AuthorizationException) ex;
			LOG.trace("AuthorizationException called");
			return handleAuthorizationException(ae, headers, status, request);
		} else if(ex instanceof AuthenticationException) {
			HttpStatus status = HttpStatus.FORBIDDEN;
			AuthenticationException aue = (AuthenticationException) ex;
			LOG.trace("AuthenticationException called");
			return handleAuthenticationException(aue, headers, status, request);
		} else {
			HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
			LOG.trace("InternalServerError called");
			return handleExceptionInternal(ex, null, headers, status, request);
		}
	}



	public ResponseEntity<ApiError> handleUserFoundException(UserFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = Collections.singletonList(ex.getMessage());
		LOG.warn("User exception was handled.", ex);
		return handleExceptionInternal(ex, new ApiError(errors), headers, status, request);
		
	}
	
	public ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = Collections.singletonList(ex.getMessage());
		LOG.warn("User exception was handled.", ex);
		return handleExceptionInternal(ex, new ApiError(errors), headers, status, request);
		//return new ResponseEntity<>("User of that id was not found, please retry your search or search via all users", HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<ApiError> handleItemNotFoundException(ItemNotFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = Collections.singletonList(ex.getMessage());
		LOG.warn("Authentication exception was handled.", ex);
		return handleExceptionInternal(ex, new ApiError(errors), headers, status, request);
		//return new ResponseEntity<>("Wrong credentials", HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = Collections.singletonList(ex.getMessage());
		LOG.warn("Authentication exception was handled.", ex);
		return handleExceptionInternal(ex, new ApiError(errors), headers, status, request);
		//return new ResponseEntity<>("Wrong credentials", HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<ApiError> handleAuthorizationException(AuthorizationException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = Collections.singletonList(ex.getMessage());
		LOG.warn("Authorization exception was handled.", ex);
		return handleExceptionInternal(ex, new ApiError(errors), headers, status, request);
		//return new ResponseEntity<>("Not Authorized", HttpStatus.FORBIDDEN);
	}
	
	private ResponseEntity<ApiError> handleExceptionInternal(Exception ex, ApiError body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
		request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
	}
		
		return new ResponseEntity<>(body, headers, status);
	}
}	