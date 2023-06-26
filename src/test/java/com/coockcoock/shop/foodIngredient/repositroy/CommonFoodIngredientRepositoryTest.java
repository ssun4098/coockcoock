package com.coockcoock.shop.foodIngredient.repositroy;

import com.coockcoock.shop.config.JpaConfig;
import com.coockcoock.shop.foodIngredient.entity.FoodIngredient;
import com.coockcoock.shop.ingredient.entity.Ingredient;
import com.coockcoock.shop.member.domain.Grade;
import com.coockcoock.shop.member.domain.Member;
import com.coockcoock.shop.recipe.entity.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@Import(JpaConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CommonFoodIngredientRepositoryTest {
    @Autowired
    CommonFoodIngredientRepository commonFoodIngredientRepository;
    @PersistenceContext
    private EntityManager entityManager;

    private Ingredient ingredient;
    private Grade grade;
    private Member member;
    private Recipe recipe;

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
    }

    @Test
    void createTeat() {
        FoodIngredient foodIngredient = FoodIngredient.builder()
                .ingredient(ingredient)
                .recipe(recipe)
                .amount("1L")
                .build();

        //when
        FoodIngredient result = commonFoodIngredientRepository.save(foodIngredient);

        //then
        assertThat(result.getAmount()).isEqualTo("1L");
        assertThat(result.getIngredient().getName()).isEqualTo(ingredient.getName());
        assertThat(result.getRecipe().getTitle()).isEqualTo(recipe.getTitle());
    }
}