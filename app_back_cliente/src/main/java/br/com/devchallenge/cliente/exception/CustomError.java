package br.com.devchallenge.cliente.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe que representa os erros retornados pela aplicação
 * @author erick.oliveira
 *
 */
@JsonRootName(value="errors")
@Getter
@Setter
@Builder
public class CustomError {
	
  
	private HttpStatus status;
	private List<String> errors;
	
}
