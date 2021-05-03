package br.com.brasilprev.beneficiario.modelo;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	public Cliente() {
	}

	public Cliente(String cpf, @NotNull @NotEmpty @Length(min = 5) String nome, @Email String email, Double valorTotal,
			Long anosReceber) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.valorTotal = valorTotal;
		this.anosReceber = anosReceber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Long getAnosReceber() {
		return anosReceber;
	}

	public void setAnosReceber(Long anosReceber) {
		this.anosReceber = anosReceber;
	}

}
