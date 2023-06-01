package org.kras.redismultithreading.configure;

import org.kras.redismultithreading.model.Student;
import org.kras.redismultithreading.util.RecordRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {
    @Bean
    public RedisTemplate<String, Student> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Student> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        RedisSerializer<String> serializer = new StringRedisSerializer();
        template.setKeySerializer(serializer);
        template.setHashKeySerializer(serializer);

        RedisSerializer<Student> studentSerializer = new RecordRedisSerializer<>(Student.class);
        template.setKeySerializer(studentSerializer);
        template.setHashKeySerializer(studentSerializer);
        return template;
    }
}
