package com.example.demo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@EnableCaching
@Configuration
@ConditionalOnProperty(prefix = "app.cache", name = "type", havingValue = "redis")
public class RedisCacheConfig {

    @Bean
    public CacheManager contentCacheManager(RedisConnectionFactory cf) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())) // Value Serializer 변경
                .entryTtl(Duration.ofMinutes(3L)); // 캐시 수명 30분

        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(cf).cacheDefaults(redisCacheConfiguration).build();
    }
}
    /*
     * Spring Boot 가 기본적으로 RedisCacheManager 를 자동 설정해줘서 RedisCacheConfiguration 없어도 사용 가능
     * Bean 을 새로 선언하면 직접 설정한 RedisCacheConfiguration 이 적용됨
     */
    /*
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(60))
                .disableCachingNullValues()
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())
                )
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())
                );
    }
*/
    /**
     * 여러 Redis Cache 에 관한 설정을 하고 싶다면 RedisCacheManagerBuilderCustomizer 를 사용할 수 있음
     */
    /*
    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> builder
                .withCacheConfiguration("cache1",
                        RedisCacheConfiguration.defaultCacheConfig()
                                .computePrefixWith(cacheName -> "prefix::" + cacheName + "::")
                                .entryTtl(Duration.ofSeconds(120))
                                .disableCachingNullValues()
                                .serializeKeysWith(
                                        RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())
                                )
                                .serializeValuesWith(
                                        RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())
                                ))
                .withCacheConfiguration("cache2",
                        RedisCacheConfiguration.defaultCacheConfig()
                                .entryTtl(Duration.ofHours(2))
                                .disableCachingNullValues());
    }
    */
