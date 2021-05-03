package br.com.brasilprev.beneficiario.controller.dto;

import br.com.brasilprev.beneficiario.modelo.Cliente;

public class DetalhesDoClienteDto {

	private String cpf;
	private String nome;
	private String email;
	private Double valorTotal;
	private Long anosReceber;

	public DetalhesDoClienteDto(Cliente cliente) {
		this.cpf = cliente.getCpf();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.valorTotal = cliente.getValorTotal();
		this.anosReceber = cliente.getAnosReceber();
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public Long getAnosReceber() {
		return anosReceber;
	}

}
