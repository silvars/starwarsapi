package com.b2w.starwars;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Ticker;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
@ConfigurationProperties(prefix = "caching")
public class CacheConfiguration {

    public static final String CACHE_PLANETS_API = "planetsFromApi";
    public static final String CACHE_PLANETS_DATABASE = "planetsFromDatabase";
    public static final String CACHE_PLANETS_BY_NAME = "planetByName";
    public static final String CACHE_PLANETS_BY_EXACT_NAME = "planetByExactName";
    public static final String CACHE_PLANETS_BY_ID = "planetById";
    public static final String CACHE_PLANETS_BY_NAME_API = "planetByNameAPI";

    private static final Integer HOUR = 60;

    private static final Integer CACHE_SIZE = 100;

    @Bean
    public CacheManager cacheManager(Ticker ticker) {
        CaffeineCache planetsFromApi = buildCache(CACHE_PLANETS_API, ticker, HOUR, CACHE_SIZE);
        CaffeineCache planetsFromDatabase = buildCache(CACHE_PLANETS_DATABASE, ticker, HOUR, CACHE_SIZE);
        CaffeineCache planetByName = buildCache(CACHE_PLANETS_BY_NAME, ticker, HOUR, CACHE_SIZE);
        CaffeineCache planetByExactName = buildCache(CACHE_PLANETS_BY_EXACT_NAME, ticker, HOUR, CACHE_SIZE);
        CaffeineCache planetById = buildCache(CACHE_PLANETS_BY_ID, ticker, HOUR, CACHE_SIZE);
        CaffeineCache planetByNameAPI = buildCache(CACHE_PLANETS_BY_NAME_API, ticker, HOUR, CACHE_SIZE);

        SimpleCacheManager manager = new SimpleCacheManager();

        manager.setCaches(Arrays.asList(planetsFromApi, planetsFromDatabase, planetByName, planetByExactName,
                planetById, planetByNameAPI));

        return manager;
    }

    private CaffeineCache buildCache(String name, Ticker ticker, Integer minutesToExpire, Integer size) {
        return new CaffeineCache(name, Caffeine.newBuilder()
                .expireAfterWrite(minutesToExpire, TimeUnit.MINUTES)
                .maximumSize(size)
                .ticker(ticker)
                .build());
    }

    @Bean
    public Ticker ticker() {
        return Ticker.systemTicker();
    }
}
