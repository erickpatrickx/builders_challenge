package br.com.devchallenge.auth.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.devchallenge.auth.dto.LoginDTO;
import br.com.devchallenge.auth.dto.TokenDTO;
import br.com.devchallenge.auth.exception.BusinessException;
import br.com.devchallenge.auth.service.AuthService;
import io.swagger.annotations.ApiOperation;

/**
 * Controller de login
 * 
 * @author erick.oliveira
 *
 */
@RestController
@RequestMapping(value = "/")
public class AuthController {

	@Autowired
	private AuthService authService;

	/**
	 * Metodo responsavel pela geração do token
	 * 
	 * @param request
	 * @param response
	 * @param login
	 * @return ResponseEntity<?>
	 * @throws IOException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(notes = "Gera�ao de token de acesso", value = "Login", response = TokenDTO.class)
	public ResponseEntity<?> login(HttpServletRequest request, HttpServletResponse response,
			@RequestBody LoginDTO login) throws IOException {
		String token = authService.login(request, response, login.getLogin(), login.getSenha());
		if (token == null) {
			throw new BusinessException("Dados invalidos");
		}
		TokenDTO tokenDTO = TokenDTO.builder().login(login.getLogin()).token(token).build();
		return new ResponseEntity<>(tokenDTO, HttpStatus.OK);
	}
	


}
