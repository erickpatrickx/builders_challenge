package br.com.devchallenge.cliente.mock;

import br.com.devchallenge.cliente.dtos.ClienteDTO;
import br.com.devchallenge.cliente.dtos.ClienteRequestDTO;

public interface ClienteRequestDTOMock {

    default ClienteRequestDTO getClienteRequestDTOMock()
    {
        return ClienteRequestDTO.builder().nome("Nome").email("email@email.com").cpf("00000000000").telefone("61999568459").idade(31).build();
    }
}
