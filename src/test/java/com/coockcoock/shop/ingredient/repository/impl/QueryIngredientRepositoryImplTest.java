package com.coockcoock.shop.ingredient.repository.impl;

import com.coockcoock.shop.config.TestConfig;
import com.coockcoock.shop.ingredient.entity.Ingredient;
import com.coockcoock.shop.ingredient.repository.QueryIngredientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Import(TestConfig.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class QueryIngredientRepositoryImplTest {
    @Autowired
    private QueryIngredientRepository queryIngredientRepository;
    @PersistenceContext
    private EntityManager entityManager;
    private Ingredient ingredient;

    @BeforeEach
    void setUp() {
        //given
        ingredient = Ingredient.builder().name("재료").build();
        entityManager.persist(ingredient);
    }

    @Test
    @DisplayName("name이 NULL 일 경우 검색 성공")
    void findByNameWhenNameIsNull() {
        //when
        Page<Ingredient> result = queryIngredientRepository.findByName(null, PageRequest.of(0, 1));
        //then
        assertThat(result.getTotalElements()).isEqualTo(1);
    }

    @Test
    @DisplayName("name이 null이 아닐 경우")
    void findByNameWhenNameIsNotNull() {
        //when
        Page<Ingredient> result = queryIngredientRepository.findByName("재료", PageRequest.of(0, 1));
        //then
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent().get(0).getName().contains("재료")).isTrue();
    }

    @Test
    @DisplayName("Id값으로 검색 실패")
    void findByIdFail() {
        Optional<Ingredient> result = queryIngredientRepository.findById(2L);

        assertThat(result).isNotPresent();
    }

    @Test
    @DisplayName("Id값으로 검색 성공")
    void findByIdSuccess() {
        Optional<Ingredient> result = queryIngredientRepository.findById(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(1L);
    }
}