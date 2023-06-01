package org.kras.redismultithreading.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class RecordRedisSerializer<T> implements RedisSerializer<T> {

    private final ObjectMapper objectMapper;
    private final Class<T> recordClass;

    public RecordRedisSerializer(Class<T> recordClass) {
        this.objectMapper = new ObjectMapper();
        this.recordClass = recordClass;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        try {
            return objectMapper.writeValueAsBytes(t);
        } catch (JsonProcessingException e) {
            throw new SerializationException("Error serializing record: " + e.getMessage(), e);
        }
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        try {
            return objectMapper.readValue(bytes, recordClass);
        } catch (Exception e) {
            throw new SerializationException("Error deserializing record: " + e.getMessage(), e);
        }
    }
}
