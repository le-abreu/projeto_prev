package br.com.brasilprev.beneficiario.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import br.com.brasilprev.beneficiario.modelo.Cliente;

public class ClienteForm {

	@CPF
	private String cpf;
	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String nome;
	@Email
	private String email;
	@NotNull
	private Double valorTotal;
	@NotNull
	private Long anosReceber;

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void setAnosReceber(Long anosReceber) {
		this.anosReceber = anosReceber;
	}

	public Cliente converter() {
		return new Cliente(this.cpf, this.nome, this.email, this.valorTotal, this.anosReceber);
	}

}
