package br.com.devchallenge.cliente.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author erick.oliveira
 *
 */
@Entity(name = "TB_CLIENTE")
@Table
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Size(max = 255)
	private String nome;

	@Size(max = 11)
	@Column(unique=true)
	private String cpf;

	private Integer idade;

	@Size(max = 100)
	private String email;

	@Size(max = 11)
	private String telefone;
}
