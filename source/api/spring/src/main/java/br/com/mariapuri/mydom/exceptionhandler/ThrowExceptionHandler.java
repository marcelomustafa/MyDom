package br.com.mariapuri.mydom.exceptionhandler;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.mariapuri.mydom.exceptionhandler.exceptionbody.ExceptionHandlerValidation;
import br.com.mariapuri.mydom.exceptionhandler.trowexceptionhandler.MyDomException;

@ControllerAdvice
public class ThrowExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(MyDomException.class)
	public ResponseEntity<Object> handleMyDomException(MyDomException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		ExceptionHandlerValidation exceptionHandlerValidation = new ExceptionHandlerValidation();

		exceptionHandlerValidation.setStatus(status.value());
		exceptionHandlerValidation.setType("");
		exceptionHandlerValidation.setTitle(status.name());
		exceptionHandlerValidation.setDetail(ex.getMessage());
		exceptionHandlerValidation.setInstance(null);
		exceptionHandlerValidation.setDate(ZonedDateTime.now(ZoneOffset.UTC));
		
		return handleExceptionInternal(ex, exceptionHandlerValidation, new HttpHeaders() , status, request);
	}
	
}
