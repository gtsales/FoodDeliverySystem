package dev.luiz.customer.system.configs;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

import dev.luiz.customer.system.constants.RedisConstants;

@Configuration
public class RedisConfig {

	@Value("${custom.redis.ttl.customer}")
	private int userTtl;
	
    @Bean
    RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> builder
                .withCacheConfiguration(RedisConstants.CUSTOMER_CACHE_NAME,
                        RedisCacheConfiguration
                        	    .defaultCacheConfig()
                        	    .entryTtl(Duration.ofMinutes(userTtl))
                                .disableCachingNullValues()
                                .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())));
	}
}
