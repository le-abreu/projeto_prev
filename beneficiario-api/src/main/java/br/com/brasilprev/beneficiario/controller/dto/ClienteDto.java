package br.com.brasilprev.beneficiario.controller.dto;

import br.com.brasilprev.beneficiario.modelo.Cliente;

public class ClienteDto {

	private String cpf;
	private String nome;

	public ClienteDto(Cliente cliente) {
		this.cpf = cliente.getCpf();
		this.nome = cliente.getNome();
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}


}
