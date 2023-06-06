package com.coockcoock.shop.config;

import com.coockcoock.shop.ingredient.repository.QueryIngredientRepository;
import com.coockcoock.shop.ingredient.repository.impl.QueryIngredientRepositoryImpl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Test Config Class
 *
 */
@TestConfiguration
public class TestConfig {
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    @Bean
    public QueryIngredientRepository queryIngredientRepository() {
        return new QueryIngredientRepositoryImpl(jpaQueryFactory());
    }
}
