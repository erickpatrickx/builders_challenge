package br.com.devchallenge.cliente.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.hibernate.exception.ConstraintViolationException;
/**
 * 
 * Controlador central de erros da aplicação
 * 
 * @author erick.oliveira
 *
 */
@ControllerAdvice
public class ExceptionController {

	/**
	 * Tratamento de BusinessException
	 * 
	 * @param BusinessException
	 * @return ResponseEntity
	 */
	@ExceptionHandler(value = BusinessException.class)
	public ResponseEntity<Object> exception(BusinessException exception) {

		if (exception.getErros() != null) {
			CustomError customError = CustomError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).errors(exception.getErros()).build();
			return new ResponseEntity<>(customError, customError.getStatus());
		}

		return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Tratamento de Exception em geral
	 * 
	 * @param exception
	 * @return ResponseEntity
	 */
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> exception(Exception exception) {

		if (exception.getMessage() != null) {
			List<String> error = new ArrayList<String>();
			error.add(exception.getMessage());
			CustomError customError = CustomError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).errors(error).build();
			return new ResponseEntity<>(customError, customError.getStatus());
		}
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Tratamento de BeanValidations
	 * 
	 * @param MethodArgumentNotValidException
	 * @return ResponseEntity
	 */
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Object> exceptionConstraint(MethodArgumentNotValidException exception) {

		if (exception.getBindingResult() != null) {

			List<String> error = new ArrayList<String>();
			exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
				error.add(fieldError.getDefaultMessage());
			});
			CustomError customError = CustomError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).errors(error).build();
			return new ResponseEntity<>(customError, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
