
package br.com.devchallenge.cliente.controller;

import br.com.devchallenge.cliente.controllers.ClienteController;
import br.com.devchallenge.cliente.dtos.ClienteDTO;
import br.com.devchallenge.cliente.mock.ClienteDTOMock;
import br.com.devchallenge.cliente.mock.ClienteMock;
import br.com.devchallenge.cliente.mock.ClienteRequestDTOMock;
import br.com.devchallenge.cliente.service.ClienteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.Silent.class)
public class ClienteControllerTest implements ClienteMock, ClienteRequestDTOMock, ClienteDTOMock {

    @Mock
    private ClienteService service;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ClienteController controller;


    @Test
    public void quando_consultar_cliente_paginados_all() {
        List<ClienteDTO> clientes = List.of(getClienteDTOMock());
        Page<ClienteDTO> pagedResponse = new PageImpl(clientes);
        when(service.findpageableAll(PageRequest.of(1, 10, Sort.by("nome").ascending()))).thenReturn(pagedResponse);
        assertNotNull(controller.getClientesPaginados(null, 1, 10).get());
    }

    @Test
    public void quando_consultar_cliente_paginados_com_filtro() {
        List<ClienteDTO> clientes = List.of(getClienteDTOMock());
        Page<ClienteDTO> pagedResponse = new PageImpl(clientes);
        when(service.findPageableByNomeOuCPF("filter", PageRequest.of(1, 10, Sort.by("nome").ascending()))).thenReturn(pagedResponse);
        assertNotNull(controller.getClientesPaginados("filter", 1, 10).get());
    }

    @Test
    public void quando_consultar_cliente_por_id_sucesso() {
        when(service.findClienteById(anyLong())).thenReturn(Optional.of(getClienteMock()));
        var response = controller.getClientePoID(1L);
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }

    @Test
    public void quando_consultar_veiculo_legal_por_id_retorna_vazio() {
        when(service.findClienteById(anyLong()))
                .thenReturn(Optional.empty());
        var response = controller.getClientePoID(1L);
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatusCodeValue());
    }

    @Test
    public void quando_incluir_cliente_sucesso() {
        ResponseEntity<ClienteDTO> response = controller.save(getClienteRequestDTOMock());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void quando_alterar_cliente_sucesso() {
        ResponseEntity<ClienteDTO> response = controller.update(getClienteRequestDTOMock(), 1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void quando_deletar_cliente_sucesso() {
        doNothing().when(service).delete(1L);
        var retorno = controller.delete(1L);
        assertEquals(HttpStatus.OK, retorno.getStatusCode());
    }


}
