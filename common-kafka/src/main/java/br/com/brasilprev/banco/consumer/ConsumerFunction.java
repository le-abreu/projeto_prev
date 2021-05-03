package br.com.brasilprev.banco.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import br.com.brasilprev.banco.Message;

public interface ConsumerFunction<T> {
    void consume(ConsumerRecord<String, Message<T>> record) throws Exception;
}
