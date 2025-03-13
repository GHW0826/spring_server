package com.example.demo.config;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@EnableCaching
@Configuration
@ConditionalOnProperty(prefix = "app.cache", name = "type", havingValue = "inmemory", matchIfMissing = true)
public class InMemoryCacheConfig {

    @Bean
    public CacheManager contentCacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
        cacheManager.setAllowNullValues(false);
        // cacheManager.setCacheNames(List.of("users", "members")); // 필요에 따라 캐시 이름 설정
        return cacheManager;
    }
}