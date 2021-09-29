package br.com.devchallenge.auth.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.devchallenge.auth.dto.LoginDTO;
import br.com.devchallenge.auth.security.TokenAuthenticationService;

/**
 * Servicos do Auth
 * 
 * @author erick.oliveira
 *
 */
@Service
public class AuthService {

	HashMap<LoginDTO, String> users = new HashMap<LoginDTO, String>();

	public AuthService() {
		LoginDTO adm = LoginDTO.builder().login("admin").senha("123456").build();
		users.put(adm, "ROLE_ADMIN");
		LoginDTO cliente = LoginDTO.builder().login("cliente").senha("123456").build();
		users.put(cliente, "ROLE_USER");
	}

	public String login(HttpServletRequest request, HttpServletResponse response, String username, String senha)
			throws IOException {
		Optional<Entry<LoginDTO, String>> op = verificaAutenticacao(username, senha);
		if (!op.isPresent()) {
			return null;
		}
		return makeAuthenication(request, response, username, op.get().getValue());
	}

	private Optional<Entry<LoginDTO, String>> verificaAutenticacao(String username, String senha) {
		Optional<Entry<LoginDTO, String>> op = users.entrySet().stream()
				.filter(user -> user.getKey().getLogin().equals(username) && user.getKey().getSenha().equals(senha))
				.findAny();
		return op;
	}

	public String makeAuthenication(HttpServletRequest request, HttpServletResponse response, String username,
			String roles) throws IOException {
		TokenAuthenticationService authenticationService = new TokenAuthenticationService();
		String userToken = authenticationService.addAuthentication(response, username, roles);
		Authentication authentication = TokenAuthenticationService.getAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return userToken;
	}

}
