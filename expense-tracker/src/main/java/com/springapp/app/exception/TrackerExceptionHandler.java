package com.springapp.app.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springapp.app.dto.ResponseDto;
import com.springapp.app.enums.ExecutionMessages;

@RestControllerAdvice
public class TrackerExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(RecordNotFoundException.class)
	public final ResponseEntity<ResponseDto> handleRecordNotFoundException(RecordNotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseDto(false,ex.getMessage(),ExecutionMessages.DEFAULT_VALUE.value()));
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
				.body(new ResponseDto(false, ex.getMessage(),ExecutionMessages.DEFAULT_VALUE.value()));
	}
	

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ResponseDto> handleOtherExceptions(Exception ex){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseDto(false,ex.getMessage(),ExecutionMessages.DEFAULT_VALUE.value()));
	}

}
