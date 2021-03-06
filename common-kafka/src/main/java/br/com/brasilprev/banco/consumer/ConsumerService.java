package br.com.brasilprev.banco.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import br.com.brasilprev.banco.Message;

public interface ConsumerService<T> {

    // you may argue that a ConsumerException would be better
    // and its ok, it can be better
    void parse(ConsumerRecord<String, Message<T>> record) throws Exception;
    String getTopic();
    String getConsumerGroup();
}
