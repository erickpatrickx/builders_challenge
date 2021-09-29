package br.com.devchallenge.cliente.mock;

import br.com.devchallenge.cliente.entity.Cliente;

public interface ClienteMock {

    default Cliente getClienteMock()
    {
        return Cliente.builder().id(1L).nome("Nome").email("email@email.com").cpf("00000000000").telefone("61999568459").idade(31).build();
    }
}
