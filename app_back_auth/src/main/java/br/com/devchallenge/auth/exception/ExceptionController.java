package br.com.devchallenge.auth.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/** Tratamento de exeções gerais do sistema
 * @author erick.oliveira
 *
 */
@ControllerAdvice
public class ExceptionController {
	
   @ExceptionHandler(value = BusinessException.class)
   public ResponseEntity<Object> exception(BusinessException exception) {
	   
	if(exception.getErros() != null) {
		   CustomError customError = CustomError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).errors(exception.getErros()).build();
		   return new ResponseEntity<>(customError, customError.getStatus());
	 }else if(exception.getMessage() != null) {
		   List<String> error = new ArrayList<String>();
		   error.add(exception.getMessage());
		   CustomError customError = CustomError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).errors(error).build();
		   return new ResponseEntity<>(customError, customError.getStatus());
	 }
	   
      return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
   }
   
   @ExceptionHandler(value = Exception.class)
   public ResponseEntity<Object> exception(Exception exception) {
	   
	if(exception.getMessage() != null) {
			List<String> error = new ArrayList<String>();
			error.add(exception.getMessage());
		   CustomError customError = CustomError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).errors(error).build();
		   return new ResponseEntity<>(customError, customError.getStatus());
	 	}
      return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
   }
   
   

}