package br.com.brasilprev.beneficiario.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.brasilprev.beneficiario.controller.dto.ClienteDto;
import br.com.brasilprev.beneficiario.controller.dto.DetalhesDoClienteDto;
import br.com.brasilprev.beneficiario.controller.form.ClienteForm;
import br.com.brasilprev.beneficiario.modelo.Cliente;
import br.com.brasilprev.beneficiario.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClientesController {
	
	@Autowired
	private ClienteService clienteService;
	
	
	@PostMapping
	@CacheEvict(value = "listaDeClientes", allEntries = true)
	public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder) {
		Cliente cliente = form.converter();
		clienteService.save(cliente);
		
		URI uri = uriBuilder.path("/clientes/{cpf}").buildAndExpand(cliente.getCpf()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDto(cliente));
	}
	
	@GetMapping("/{cpf}")
	public ResponseEntity<DetalhesDoClienteDto> detalhar(@PathVariable String cpf) {
		Cliente cliente = clienteService.findByCpf(cpf);
		if(cliente != null) {
			return ResponseEntity.ok(new DetalhesDoClienteDto(cliente));
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	

}