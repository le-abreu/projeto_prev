package br.com.brasilprev.beneficiario.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.brasilprev.beneficiario.controller.dto.ClienteDto;
import br.com.brasilprev.beneficiario.controller.dto.DetalhesDoClienteDto;
import br.com.brasilprev.beneficiario.controller.form.AtualizacaoClienteForm;
import br.com.brasilprev.beneficiario.controller.form.ClienteForm;
import br.com.brasilprev.beneficiario.modelo.Cliente;
import br.com.brasilprev.beneficiario.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClientesController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@GetMapping
	@Cacheable(value = "listaDeClientes")
	public Page<ClienteDto> lista(@RequestParam(required = false) String nomeCurso, 
			@PageableDefault(sort = "nome", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {
		
		if (nomeCurso == null) {
			Page<Cliente> clientes = clienteRepository.findAll(paginacao);
			return ClienteDto.converter(clientes);
		} else {
			Page<Cliente> clientes = clienteRepository.findByNome(nomeCurso, paginacao);
			return ClienteDto.converter(clientes); 
		}
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "listaDeClientes", allEntries = true)
	public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder) {
		Cliente Cliente = form.converter();
		clienteRepository.save(Cliente);
		
		URI uri = uriBuilder.path("/clientes/{cpf}").buildAndExpand(Cliente.getCpf()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDto(Cliente));
	}
	
	@GetMapping("/{cpf}")
	public ResponseEntity<DetalhesDoClienteDto> detalhar(@PathVariable String cpf) {
		Optional<Cliente> Cliente = clienteRepository.findById(cpf);
		if (Cliente.isPresent()) {	
			return ResponseEntity.ok(new DetalhesDoClienteDto(Cliente.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{cpf}")
	@Transactional
	@CacheEvict(value = "listaDeClientes", allEntries = true)
	public ResponseEntity<ClienteDto> atualizar(@PathVariable String cpf, @RequestBody @Valid AtualizacaoClienteForm form) {
		Optional<Cliente> optional = clienteRepository.findById(cpf);
		if (optional.isPresent()) {
			Cliente Cliente = form.atualizar(cpf, clienteRepository);
			return ResponseEntity.ok(new ClienteDto(Cliente));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{cpf}")
	@Transactional
	@CacheEvict(value = "listaDeClientes", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable String cpf) {
		Optional<Cliente> optional = clienteRepository.findById(cpf);
		if (optional.isPresent()) {
			clienteRepository.deleteById(cpf);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}

}