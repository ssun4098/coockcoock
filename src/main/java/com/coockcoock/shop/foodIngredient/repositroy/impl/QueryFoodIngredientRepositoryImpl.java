package com.coockcoock.shop.foodIngredient.repositroy.impl;

import com.coockcoock.shop.foodIngredient.entity.FoodIngredient;
import com.coockcoock.shop.foodIngredient.entity.querydsl.QFoodIngredient;
import com.coockcoock.shop.foodIngredient.repositroy.QueryFoodIngredientRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class QueryFoodIngredientRepositoryImpl implements QueryFoodIngredientRepository {
    private final JPAQueryFactory jpaQueryFactory;

    /**
     *{@inheritDoc}
     */
    @Override
    public List<FoodIngredient> findByRecipeId(Long recipeId) {
        QFoodIngredient foodIngredient = QFoodIngredient.foodIngredient;
        return jpaQueryFactory
                .selectFrom(foodIngredient)
                .where(foodIngredient.recipe.id.eq(recipeId))
                .fetch();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void deleteByRecipeId(Long recipeId) {
        QFoodIngredient foodIngredient = QFoodIngredient.foodIngredient;
        jpaQueryFactory.delete(foodIngredient)
                .where(foodIngredient.recipe.id.eq(recipeId))
                .execute();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void deleteByIngredientId(Long ingredientId) {
        QFoodIngredient foodIngredient = QFoodIngredient.foodIngredient;
        jpaQueryFactory.delete(foodIngredient)
                .where(foodIngredient.ingredient.id.eq(ingredientId))
                .execute();
    }
}
