package com.learn.urlshorten.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.urlshorten.model.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

    @Autowired
    private ObjectMapper  objectMapper;
    @Autowired
    private RedisConnectionFactory connectionFactory;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    RedisTemplate<String, Url> redisTemplate() {
        final RedisTemplate<String, Url> redisTemplate = new RedisTemplate<>();
        Jackson2JsonRedisSerializer valueSerializer = new Jackson2JsonRedisSerializer(Url.class);
        valueSerializer.setObjectMapper(objectMapper);
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(valueSerializer);
        return redisTemplate;
    }

}
