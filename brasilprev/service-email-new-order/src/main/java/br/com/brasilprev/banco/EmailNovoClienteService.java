package br.com.brasilprev.banco;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import br.com.brasilprev.banco.consumer.ConsumerService;
import br.com.brasilprev.banco.consumer.ServiceRunner;
import br.com.brasilprev.banco.dispatcher.KafkaDispatcher;
import br.com.brasilprev.banco.modelo.Cliente;

public class EmailNovoClienteService implements ConsumerService<Cliente> {

    public static void main(String[] args) {
        new ServiceRunner(EmailNovoClienteService::new).start(1);
    }

    private final KafkaDispatcher<String> emailDispatcher = new KafkaDispatcher<>();

    public void parse(ConsumerRecord<String, Message<Cliente>> record) throws ExecutionException, InterruptedException {
        var message = record.value();

        var cliente = message.getPayload();
        
        var emailCode = "Obrigado por realizar seu cadastro, em breve seus valores estaram disponiveis!";
        var id = message.getId().continueWith(EmailNovoClienteService.class.getSimpleName());
        emailDispatcher.send("CLIENTE_SEND_EMAIL", cliente.getEmail(),
                id, emailCode);

    }

    @Override
    public String getTopic() {
        return "ECOMMERCE_NEW_CLIENTE";
    }

    @Override
    public String getConsumerGroup() {
        return EmailNovoClienteService.class.getSimpleName();
    }

}
