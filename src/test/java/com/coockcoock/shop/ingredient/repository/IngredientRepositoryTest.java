package com.coockcoock.shop.ingredient.repository;

import com.coockcoock.shop.ingredient.entity.Ingredient;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class IngredientRepositoryTest {
    @Autowired
    private CommandIngredientRepository commandIngredientRepository;

    @Test
    @DisplayName("생성 테스트")
    void createTest() {
        //given
        Ingredient ingredient = Ingredient.builder()
                .name("name")
                .build();
        //when
        commandIngredientRepository.save(ingredient);
        List<Ingredient> result = commandIngredientRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo(ingredient.getName());
    }

    @Test
    @DisplayName("삭제 테스트")
    void deleteTest() {
        //given
        Ingredient ingredient = Ingredient.builder()
                .name("name")
                .build();
        commandIngredientRepository.save(ingredient);

        //when
        commandIngredientRepository.deleteById(2L);
        List<Ingredient> result = commandIngredientRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(0);
    }
}