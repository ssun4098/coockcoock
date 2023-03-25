package com.coockcoock.shop.config;


import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

/**
 * QueryDsl 관련 Config
 *
 * @version 1.0
 * @since 23-03-25
 */
@Configuration
public class QueryDslConfig {

    /**
     * QueryDsl 사용을 위한 JPAQueryFactory를 SpringBean으로 등록
     *
     * @param em JPAQueryFactory 등록을 위한 EntityManager
     * @return JPAQueryFactory
     * @since 23-03-25
     */
    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager em) {
        return new JPAQueryFactory(em);
    }
}
