package br.com.brasilprev.banco.modelo;

import java.io.Serializable;

public class Cliente implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String cpf;
		private String nome;
		private String email;
		private Double valorTotal;
		private Long anosReceber;

		public Cliente(String cpf, String nome, String email, Double valorTotal,
				Long anosReceber) {
			super();
			this.cpf = cpf;
			this.nome = nome;
			this.email = email;
			this.valorTotal = valorTotal;
			this.anosReceber = anosReceber;
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


    @Override
    public String toString() {
        return "Cliente{" +
                "cpf='" + cpf + 
                ", nome=" + nome +
                ", email='" + email + 
                ", valorTotal='" + valorTotal + 
                ", anosReceber='" + anosReceber + 
                '}';
    }
}
