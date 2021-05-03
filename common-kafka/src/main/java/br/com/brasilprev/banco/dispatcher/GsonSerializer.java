package br.com.brasilprev.banco.dispatcher;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.brasilprev.banco.Message;
import br.com.brasilprev.banco.MessageAdapter;

import org.apache.kafka.common.serialization.Serializer;

public class GsonSerializer<T> implements Serializer<T> {

    private final Gson gson = new GsonBuilder().registerTypeAdapter(Message.class, new MessageAdapter()).create();

    @Override
    public byte[] serialize(String s, T object) {
        return gson.toJson(object).getBytes();
    }
}
