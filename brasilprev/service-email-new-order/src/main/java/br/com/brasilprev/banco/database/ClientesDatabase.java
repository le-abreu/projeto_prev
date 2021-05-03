package br.com.brasilprev.banco.database;

import java.io.Closeable;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.brasilprev.banco.modelo.Cliente;

public class ClientesDatabase implements Closeable {

    private final LocalDatabase database;

    public ClientesDatabase() throws SQLException {
        this.database = new LocalDatabase("clientes_database");
        // you might want to save all data
        this.database.createIfNotExists("create table clientes (" +
                "cpf varchar(200) primary key,"+
                "nome varchar(200) not null,"+
                "email varchar(200) not null,"+
                "valorTotal number(8,2) not null,"+
                "anosReceber number(19,0) not null)");
        
    }

    public boolean saveNew(Cliente cliente) throws SQLException {
        if(wasProcessed(cliente)) {
            return false;
        }

        database.query("insert into clientes (cpf, nome, email, valorTotal, anosReceber) values (?,?,?,?,?)", 
        		cliente.getCpf(),
        		cliente.getNome(),
        		cliente.getEmail(),
        		cliente.getValorTotal(),
        		cliente.getAnosReceber());
        return true;
    }

    private boolean wasProcessed(Cliente cliente) throws SQLException {
        var results = database.query("select cpf from clientes where cpf = ? limit 1", cliente.getCpf());
        return results.next();
    }

    @Override
    public void close() throws IOException {
        try {
            database.close();
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

	public Cliente findByCpf(String cpf) throws SQLException {
        ResultSet results;
		try {
			results = database.query("select cpf, nome, email, valorTotal, anosReceber from clientes where cpf = ? limit 1", cpf);
			if(results.isFirst()) {
				Cliente cliente = new Cliente(results.getString("cpf"), 
						results.getString("nome"), results.getString("email"), results.getDouble("valorTotal"), results.getLong("anosReceber"));
				return cliente;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new SQLException("Dados não encontrado");
	}
}
