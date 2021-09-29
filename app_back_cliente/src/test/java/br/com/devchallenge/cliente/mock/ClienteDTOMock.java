package br.com.devchallenge.cliente.mock;

import br.com.devchallenge.cliente.dtos.ClienteDTO;

public interface ClienteDTOMock {

    default ClienteDTO getClienteDTOMock()
    {
        return ClienteDTO.builder().id(1L).nome("Nome").email("email@email.com").cpf("00000000000").telefone("61999568459").idade(31).build();
    }
}
