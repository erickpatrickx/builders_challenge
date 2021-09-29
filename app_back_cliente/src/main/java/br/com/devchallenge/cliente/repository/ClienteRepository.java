package br.com.devchallenge.cliente.repository;

import br.com.devchallenge.cliente.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {


	@Query(value = "select cliente from TB_CLIENTE cliente order by cliente.nome asc " )
	Page<Cliente> findPageable(Pageable pageable);

	@Query(value = "select cliente from TB_CLIENTE cliente where lower(cliente.nome)  like  lower(concat('%', :query,'%')) or cliente.cpf  like  concat('%', :query,'%')  order by cliente.nome asc")
	Page<Cliente> findPageablNomeOuCPF(String query, Pageable pageable);


	@Query(value = "select cliente from TB_CLIENTE cliente where  cliente.cpf  =  :cpf and (:id is null or :id != cliente.id)")
	Optional<Cliente> findPorCPF(String cpf,Long id);

}


