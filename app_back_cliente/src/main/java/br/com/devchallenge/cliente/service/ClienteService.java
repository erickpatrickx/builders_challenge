package br.com.devchallenge.cliente.service;

import java.util.Optional;

import br.com.devchallenge.cliente.dtos.ClienteDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.devchallenge.cliente.entity.Cliente;
import br.com.devchallenge.cliente.exception.BadRequestException;
import br.com.devchallenge.cliente.exception.BusinessException;
import br.com.devchallenge.cliente.repository.ClienteRepository;

/**
 * Servicos da cliente
 *
 * @author erick.oliveira
 *
 */
/**
 * @author erick.oliveira
 *
 */
@Service
public class ClienteService {

	ClienteRepository repository;

	private ModelMapper modelMapper;

	public ClienteService(ClienteRepository repository,ModelMapper modelMapper) {
		this.repository = repository;
		this.modelMapper = modelMapper;
	}

	/**
	 * Metodo de buscar um cliente por nome
	 *
	 * @param  pageable
	 * @return List<ClienteDTO>
	 */
	public Page<ClienteDTO> findpageableAll(Pageable pageable) {
		return repository.findPageable(pageable).map(this::toDTO);
	}



	/**
	 * Metodo de buscar um cliente por nome
	 *
	 * @param query
	 * @param  pageable
	 * @return List<ClienteDTO>
	 */
	public Page<ClienteDTO> findPageableByNomeOuCPF(String query, Pageable pageable) {
		return repository.findPageablNomeOuCPF(query,pageable).map(this::toDTO);
	}

	/**
	 * Metodo de buscar um cliente por id
	 *
	 * @param id
	 * @return List<cliente>
	 */
	public Optional<Cliente> findClienteById(Long id) {
		return repository.findById(id) ;
	}

	/**
	 * Metodo de salvar um cliente
	 *
	 * @param cliente
	 * @return cliente
	 */
	public Cliente save(Cliente cliente) {
		verificarClienteExistente(cliente);
		return 	repository.save(cliente);
	}

	/**
	 * Metodo de atualizar um cliente
	 *
	 * @param cliente
	 * @param id
	 * @return cliente
	 */
	public Cliente update(Cliente cliente, Long id) {
		if(!repository.findById(id).isPresent()) {
			throw new BadRequestException("Entidade não encontrada");
		}
		cliente.setId(id);
		verificarClienteExistente(cliente);
		return repository.save(cliente);
	}


	private void verificarClienteExistente(Cliente cliente){
		if(repository.findPorCPF(cliente.getCpf(),cliente.getId()).isPresent()) {
			throw new BadRequestException("Entidade já cadastrada");
		}
	}

	/**
	 * Metodo de atualizar um cliente
	 *
	 * @param id
	 * @return cliente
	 */
	public void delete(Long id) {
		if(!repository.findById(id).isPresent()) {
			throw new BadRequestException("Entidade  não encontrada");
		}
		repository.deleteById(id);
	}


	public ClienteDTO toDTO(Cliente cliente) {
		if (cliente == null) {
			return null;
		} else {
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO = modelMapper.map(cliente, ClienteDTO.class);
			return clienteDTO;
		}
	}
}
