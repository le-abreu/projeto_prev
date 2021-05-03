package br.com.brasilprev.banco.consumer;

public interface ServiceFactory<T> {
    ConsumerService<T> create() throws Exception;
}
