package br.com.devchallenge.auth.dto;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe que representa o retorno do token
 * 
 * @author erick.oliveira
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonRootName(value = "token")
public class TokenDTO {

	private String login, token;

}