package com.coockcoock.shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Redis Config 클래스
 *
 * @since 23-05-11
 * @version 1.0
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;

    /**
     * Redis와의 커넥션을 설정한다.(Redis의 커넥션 풀을 다룰 수도 있음)
     * 추후 Redis 클러스터링이나 센티넬 구조를 다룰 때 변경 예정
     *
     * @return RedisConnectionFactory를 반환
     * @since 23-05-11
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(host, port);
    }

    /**
     * RedisTemplate을 스프링 빈으로 등록한다.
     *
     * @return RedisTemplate 객체
     */
    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }
}
