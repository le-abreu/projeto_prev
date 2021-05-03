package br.com.brasilprev.beneficiario.controller.form;

import javax.validation.constraints.NotNull;

import br.com.brasilprev.beneficiario.modelo.Cliente;
import br.com.brasilprev.beneficiario.repository.ClienteRepository;

public class AtualizacaoClienteForm {
	
	@NotNull
	private Double valorTotal;

	@NotNull
	private Long anosReceber;

	public Cliente atualizar(String cpf, ClienteRepository clienteRepository) {
		Cliente cliente = clienteRepository.getOne(cpf);
		cliente.setValorTotal(this.valorTotal);
		cliente.setAnosReceber(this.anosReceber);
		return cliente;
	}
	
}
