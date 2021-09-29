package br.com.devchallenge.cliente.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author erick.oliveira
 *
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteDTO {

	private Long id;

	private String nome;

	private String cpf;

	private Integer idade;

	private String email;

	private String telefone;

}
