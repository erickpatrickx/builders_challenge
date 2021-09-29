package br.com.devchallenge.auth.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.devchallenge.auth.dto.LoginDTO;

/**
 * Teste para controllers do Auth
 * 
 * @author erick.oliveira
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AuthControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	LoginDTO login = LoginDTO.builder().login("admin").senha("123456").build();
	LoginDTO loginFail = LoginDTO.builder().login("admin").senha("12345684 ").build();

	@Test
	public void deveRealizarLoginRetornarToken() throws Exception {
		mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(login)).header("Content-Type", "application/json"))
				.andExpect(status().isOk());
	}

	@Test
	public void naoDeveRealizarLoginRetornarToken() throws Exception {
		mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(loginFail)).header("Content-Type", "application/json"))
				.andExpect(status().isInternalServerError());
	}

}
