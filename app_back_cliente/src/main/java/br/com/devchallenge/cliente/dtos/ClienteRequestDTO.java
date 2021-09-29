package br.com.devchallenge.cliente.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author erick.oliveira
 *
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteRequestDTO {

	@NotEmpty(message = "Nome não informado")
	@Size(min=3,max=100,message="Campo Nome: Limite de caracteres excedido - minimo 3 e maximo 250")
	private String nome;

	@NotEmpty(message = "CPF não informado")
	@Pattern(regexp="([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})",message="CPF Invalido")
	private String cpf;

	@NotNull(message = "Idade não informada")
	private Integer idade;

	@Email(message = "E-mail invalido")
	private String email;

	private String telefone;

}
