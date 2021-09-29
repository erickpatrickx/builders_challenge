package br.com.devchallenge.auth.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe que representa os dados de login do usuario 
 * @author erick.oliveira
 *
 */
@Getter
@Setter
@Builder
public class LoginDTO {
	
	public LoginDTO() {}
	
	public LoginDTO(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
	
	@ApiModelProperty(required=true)
	private String login, senha;

	
}