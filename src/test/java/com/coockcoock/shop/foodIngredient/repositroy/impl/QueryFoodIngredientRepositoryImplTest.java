package com.coockcoock.shop.foodIngredient.repositroy.impl;

import com.coockcoock.shop.foodIngredient.entity.FoodIngredient;
import com.coockcoock.shop.foodIngredient.repositroy.QueryFoodIngredientRepository;
import com.coockcoock.shop.ingredient.entity.Ingredient;
import com.coockcoock.shop.member.domain.Grade;
import com.coockcoock.shop.member.domain.Member;
import com.coockcoock.shop.recipe.entity.Recipe;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class QueryFoodIngredientRepositoryImplTest {
    @Autowired
    QueryFoodIngredientRepository queryFoodIngredientRepository;
    @PersistenceContext
    private EntityManager entityManager;

    private Ingredient ingredient;
    private Grade grade;
    private Member member;
    private Recipe recipe;
    private FoodIngredient foodIngredient;

    @BeforeEach
    void setUp() {
        //given
        ingredient = Ingredient.builder()
                .name("ingredient")
                .build();
        entityManager.persist(ingredient);

        grade = Grade.builder()
                .name("DEFAULT")
                .build();
        entityManager.persist(grade);

        member = Member
                .builder()
                .loginId("loginId")
                .password("password")
                .signUpDate(LocalDate.now())
                .ban(false)
                .banReason(null)
                .grade(grade)
                .build();
        entityManager.persist(member);

        recipe = Recipe.builder()
                .title("title")
                .cookery("cookery")
                .member(member)
                .build();
        entityManager.persist(recipe);

        foodIngredient = FoodIngredient.builder()
                .ingredient(ingredient)
                .recipe(recipe)
                .build();
        entityManager.persist(foodIngredient);
    }

    @Test
    @Order(1)
    void testFindByRecipeId() {
        //when
        List<FoodIngredient> results = queryFoodIngredientRepository.findByRecipeId(1L);
        //then
        assertThat(results.size()).isEqualTo(1);
    }

    @Test
    @Order(2)
    void testDeleteByRecipeId() {
        //when
        queryFoodIngredientRepository.deleteByRecipeId(1L);
        List<FoodIngredient> results = queryFoodIngredientRepository.findByRecipeId(1L);

        //then
        assertThat(results.size()).isZero();
    }

    @Test
    @Order(3)
    void testDeleteByIngredientId() {
        //when
        queryFoodIngredientRepository.deleteByIngredientId(3L);
        List<FoodIngredient> results = queryFoodIngredientRepository.findByRecipeId(3L);

        //then
        assertThat(results.size()).isZero();
    }
}