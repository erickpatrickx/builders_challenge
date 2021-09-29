package br.com.devchallenge.cliente.controllers;

import br.com.devchallenge.cliente.dtos.ClienteDTO;
import br.com.devchallenge.cliente.dtos.ClienteRequestDTO;
import br.com.devchallenge.cliente.entity.Cliente;
import br.com.devchallenge.cliente.exception.BadRequestException;
import br.com.devchallenge.cliente.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 *
 * Controller do microservice de Cliente
 *
 * @author erick.oliveira
 *
 */
@RestController
@RequestMapping("/cliente")
public class ClienteController {

	private final ClienteService service;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	public ClienteController(ClienteService service,ModelMapper modelMapper) {
		this.service = service;
		this.modelMapper = modelMapper;
	}

	/**
	 * Consultar todos os clientes paginado com ou sem filtro cpf ou nome
	 *
	 * @param query
	 * @param pageIndex
	 * @param pageSize
	 * @return ResponseEntity
	 */
	@GetMapping
	public Page<ClienteDTO> getClientesPaginados(@RequestParam(value = "q",required = false) String query, @RequestParam(value = "page",defaultValue = "0") int pageIndex,
								   @RequestParam(value = "size",defaultValue = "1") int pageSize) {
		if(Objects.isNull(query)){
			return service.findpageableAll(PageRequest.of(pageIndex, pageSize, Sort.by("nome").ascending()));
		}
		return  service.findPageableByNomeOuCPF(query, PageRequest.of(pageIndex, pageSize,Sort.by("nome").ascending()));
	}

	/**
	 * Consultar Cliente por ID
	 *
	 * @param id
	 * @return ResponseEntity
	 */
	@GetMapping("/{id}")
	@ApiOperation(notes = "Consultar cliente por ID", value = "Consultar cliente por ID", response = ResponseEntity.class)
    @Secured("ROLE_ADMIN")
	public ResponseEntity<ClienteDTO> getClientePoID(@PathVariable Long id) {

		var response = service.findClienteById(id);
		if (response.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(modelMapper.map(response.get(), ClienteDTO.class));
	}

	/**
	 * Metodo responsavel por salvar uma Cliente
	 *
	 * @param dto
	 * @return ResponseEntity
	 */
	@PostMapping()
	@ApiOperation(notes = "Salvar Cliente", value = "Salvar Cliente", response = ResponseEntity.class)
    @Secured("ROLE_ADMIN")
	public ResponseEntity<ClienteDTO> save(@Validated @RequestBody ClienteRequestDTO dto) {
		Cliente cliente =  modelMapper.map(dto, Cliente.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(service.save(cliente),ClienteDTO.class));
	}

	/**
	 * Metodo responsavel por atualizar uma Cliente
	 *
	 * @param id
	 * @param dto
	 * @return ResponseEntity
	 */
	@PutMapping("{id}")
	@ApiOperation(notes = "Atualizar Cliente", value = "Atualizar Cliente", response = ResponseEntity.class)
    @Secured("ROLE_ADMIN")
	public ResponseEntity<ClienteDTO> update(@Validated @RequestBody ClienteRequestDTO dto, @PathVariable  Long id)
			 {
		Cliente cliente =  modelMapper.map(dto, Cliente.class);
		return ResponseEntity.ok().body(modelMapper.map(service.update(cliente,id),ClienteDTO.class));
	}

	/**
	 * Metodo responsavel por deletar uma Cliente
	 *
	 * @param id
	 * @return ResponseEntity
	 */
	@DeleteMapping("{id}")
	@ApiOperation(notes = "Deletar Cliente", value = "Deletar Cliente", response = ResponseEntity.class)
    @Secured("ROLE_ADMIN")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		if (id == null) {
			throw new BadRequestException("Informe o id");
		}
		service.delete(id);
		return ResponseEntity.ok().build();
	}


}
