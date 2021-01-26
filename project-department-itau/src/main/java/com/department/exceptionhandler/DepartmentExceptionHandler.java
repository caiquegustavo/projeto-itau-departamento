package com.department.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.department.service.exception.DepartmentAlreadyRegisteredException;

import lombok.Getter;

@ControllerAdvice
public class DepartmentExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	private static final String status = "FAILED";
	
	@ExceptionHandler({ DepartmentAlreadyRegisteredException.class })
	public ResponseEntity<Object> handleDepartmentAlreadyRegistered(DepartmentAlreadyRegisteredException ex){
		
		String messages = messageSource.getMessage("department.already.registered", null, LocaleContextHolder.getLocale());
		List<ErrorDepartment> errorsDepartment = Arrays.asList(new ErrorDepartment(status, messages));
	
		return ResponseEntity.badRequest().body(errorsDepartment);
	}
	
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccess(EmptyResultDataAccessException ex, WebRequest webRequest) {
		
		String messages = messageSource.getMessage("resource.not.found", null, LocaleContextHolder.getLocale());
		List<ErrorDepartment> errorsDepartment = Arrays.asList(new ErrorDepartment(status, messages));
		
		return handleExceptionInternal(ex, errorsDepartment, new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders httpHeaders, HttpStatus httpStatus, WebRequest webRequest) {

		List<ErrorDepartment> errorsDepartment = createErrorList(ex.getBindingResult());

		return handleExceptionInternal(ex, errorsDepartment, httpHeaders, HttpStatus.BAD_REQUEST, webRequest);
	}
	
	private List<ErrorDepartment> createErrorList(BindingResult bindingResult){
		
		List<ErrorDepartment> errorsDepartment = new ArrayList<>();
		
		for(FieldError fieldError : bindingResult.getFieldErrors()) {
			String messages = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			errorsDepartment.add(new ErrorDepartment(status, messages));
		}
			
		return errorsDepartment;
	}
	
	@Getter
	public static class ErrorDepartment{
		
		private String status;
		private String messages;
		
		public ErrorDepartment(String status, String messages) {
			this.status = status;
			this.messages = messages;
		}
	}

}
