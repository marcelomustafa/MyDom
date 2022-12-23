package br.com.mariapuri.mydom.exceptionhandler;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.mariapuri.mydom.exceptionhandler.exceptionbody.ExceptionHandlerValidation;
import lombok.AllArgsConstructor;

@ControllerAdvice
@AllArgsConstructor
public class RequestExceptionHandler extends ResponseEntityExceptionHandler {
	
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<ExceptionHandlerValidation.Field> fields = new ArrayList<>();
		
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			String name = ((FieldError) error).getField();
			//String message = error.getDefaultMessage();
			String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			fields.add(new ExceptionHandlerValidation.Field(name, message));
		}

		ExceptionHandlerValidation exceptionHandlerValidation = new ExceptionHandlerValidation();

		exceptionHandlerValidation.setStatus(status.value());
		exceptionHandlerValidation.setType("");
		exceptionHandlerValidation.setTitle("Invalid Value");
		exceptionHandlerValidation.setDetail(ex.getMessage());
		exceptionHandlerValidation.setInstance(null);
		exceptionHandlerValidation.setDate(ZonedDateTime.now(ZoneOffset.UTC));

		return handleExceptionInternal(ex, exceptionHandlerValidation, headers, status, request);
	}	
		
}
