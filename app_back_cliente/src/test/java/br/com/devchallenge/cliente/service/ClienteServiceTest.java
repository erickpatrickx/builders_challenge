package br.com.devchallenge.cliente.service;

import br.com.devchallenge.cliente.entity.Cliente;
import br.com.devchallenge.cliente.exception.BadRequestException;
import br.com.devchallenge.cliente.mock.ClienteDTOMock;
import br.com.devchallenge.cliente.mock.ClienteMock;
import br.com.devchallenge.cliente.mock.ClienteRequestDTOMock;
import br.com.devchallenge.cliente.repository.ClienteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ClienteServiceTest implements ClienteMock, ClienteRequestDTOMock, ClienteDTOMock {

    @Mock
    private ClienteRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ClienteService service;


    @Test
    public void quando_consultar_cliente_paginados_all() {
        List<Cliente> clientes = List.of(getClienteMock());
        Page<Cliente> pagedResponse = new PageImpl(clientes);
        when(repository.findPageable(PageRequest.of(1, 10))).thenReturn(pagedResponse);
        assertNotNull(service.findpageableAll(PageRequest.of(1, 10)));
    }

    @Test
    public void quando_consultar_cliente_paginados_com_filtro() {
        List<Cliente> clientes = List.of(getClienteMock());
        Page<Cliente> pagedResponse = new PageImpl(clientes);
        when(repository.findPageablNomeOuCPF("filter", PageRequest.of(1, 10))).thenReturn(pagedResponse);
        assertNotNull(service.findPageableByNomeOuCPF("filter", PageRequest.of(1, 10)));
    }


    @Test
    public void quando_consultar_cliente_por_id_sucesso() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(getClienteMock()));
        var response = service.findClienteById(1L);
        assertNotNull(response);
    }

    @Test
    public void quando_consultar_veiculo_legal_por_id_retorna_vazio() {
        when(repository.findById(anyLong()))
                .thenReturn(Optional.empty());
        var response = service.findClienteById(1L);
        assertTrue(response.isEmpty());
    }

    @Test
    public void quando_incluir_cliente_sucesso() {
        when(repository.save(getClienteMock())).thenReturn(getClienteMock());
        when(repository.findPorCPF(getClienteMock().getCpf(), null)).thenReturn(Optional.empty());
        assertEquals(getClienteMock(), service.save(getClienteMock()));
    }


    @Test(expected = BadRequestException.class)
    public void quando_incluir_cliente_exception_ja_incluido() {
        when(repository.findPorCPF(getClienteMock().getCpf(), null)).thenReturn(Optional.of(getClienteMock()));
        Cliente cliente = getClienteMock();
        cliente.setId(null);
        service.save(cliente);
    }

    @Test
    public void quando_alterar_cliente_sucesso() {
        when(repository.findById(1L)).thenReturn(Optional.of(getClienteMock()));
        when(repository.save(getClienteMock())).thenReturn(getClienteMock());
        when(repository.findPorCPF(getClienteMock().getCpf(), null)).thenReturn(Optional.empty());

        assertEquals(getClienteMock(), service.update(getClienteMock(), 1L));
    }

    @Test(expected = BadRequestException.class)
    public void quando_alterar_cliente_exception_ja_incluido() {
        when(repository.findPorCPF(getClienteMock().getCpf(), getClienteMock().getId())).thenReturn(Optional.of(getClienteMock()));
        service.save(getClienteMock());
    }

    @Test(expected = BadRequestException.class)
    public void quando_alterar_cliente_exception_entidade_nao_encontrada() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
         service.update(getClienteMock(), 1L);
    }

    @Test
    public void quando_deletar_cliente_sucesso() {
        when(repository.findById(1L)).thenReturn(Optional.of(getClienteMock()));
        doNothing().when(repository).delete(getClienteMock());
        service.delete(1l);
    }

    @Test(expected = BadRequestException.class)
    public void quando_deletar_cliente_exception_entidade_nao_encontrada() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        service.delete(1L);
    }


}
