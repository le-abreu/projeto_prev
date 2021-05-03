package br.com.brasilprev.beneficiario.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brasilprev.beneficiario.modelo.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

	Page<Cliente> findByNome(String nome, Pageable paginacao);

}
