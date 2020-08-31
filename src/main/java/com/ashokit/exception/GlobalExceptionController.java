package com.ashokit.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController {

	@ExceptionHandler(ResourceCreationException.class)
	public ResponseEntity<?> handleResouceCreationException(ResourceCreationException exception){

		CustomErrorResponse errorResp=new CustomErrorResponse("NOT_CREATED_ERROR",exception.getMessage());
		errorResp.setTimestamp(LocalDateTime.now());
		errorResp.setStatus(HttpStatus.NO_CONTENT.value());
			
		return new ResponseEntity<>(errorResp,HttpStatus.NO_CONTENT);
	}	

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> handleNotFoundException(NotFoundException exception){

		CustomErrorResponse errorResp=new CustomErrorResponse("NOT_FOUND_ERROR",exception.getMessage());
		errorResp.setTimestamp(LocalDateTime.now());
		errorResp.setStatus(HttpStatus.NOT_FOUND.value());
			
		return new ResponseEntity<>(errorResp,HttpStatus.NOT_FOUND);
	}	

		@ExceptionHandler(Exception.class)
		public ResponseEntity<?> handleOtherInternalException(Exception exception){
	
			CustomErrorResponse errorResp=new CustomErrorResponse("INTERNAL_SERVER_ERROR",exception.getMessage());
			errorResp.setTimestamp(LocalDateTime.now());
			errorResp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				
			return new ResponseEntity<>(errorResp,HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	

}
