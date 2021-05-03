package br.com.brasilprev.beneficiario.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import br.com.brasilprev.banco.CorrelationId;
import br.com.brasilprev.banco.dispatcher.KafkaDispatcher;
import br.com.brasilprev.beneficiario.database.ClientesDatabase;
import br.com.brasilprev.beneficiario.modelo.Cliente;

@Service
public class ClienteService {



	public String save(Cliente cliente) {
		KafkaDispatcher<Cliente> orderDispatcher = new KafkaDispatcher<>();

            try (ClientesDatabase database = new ClientesDatabase()) {
                if (database.saveNew(cliente)) {
                    orderDispatcher.send("ECOMMERCE_NEW_CLIENTE", cliente.getEmail(),
                            new CorrelationId(ClienteService.class.getSimpleName()),
                            cliente);

                    return "Novo cadastro sucesso.";
                } else {
                    return "Já existe cadastro.";
                }
            } catch (IOException | InterruptedException | SQLException | ExecutionException e) {
            	throw new RuntimeException(e);

            }
	}

	public Cliente findByCpf(String cpf) {
		try (ClientesDatabase database = new ClientesDatabase()) {
            return database.findByCpf(cpf);
        } catch (IOException | SQLException e) {
        	throw new RuntimeException(e);
        }
	}

    
}
