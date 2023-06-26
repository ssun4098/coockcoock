package com.coockcoock.shop.foodIngredient.repositroy;

import com.coockcoock.shop.foodIngredient.entity.FoodIngredient;

import java.util.List;

/**
 * QueryDSL을 이용한 Repository Read 담당
 *
 * @since 23-06-26
 */
public interface QueryFoodIngredientRepository {

    /**
     * 레시피 id로 해당 레시피의 재료를 찾는 메서드
     *
     * @param recipeId 찾을 레시피의 id
     * @return 사용한 재료 리스트
     */
    List<FoodIngredient> findByRecipeId(Long recipeId);

    /**
     * 레시피 id로 사용한 재료 삭제 메서드
     *
     * @param recipeId 삭제할 recipeId
     */
    void deleteByRecipeId(Long recipeId);

    /**
     * 재료 id로 재료 삭제 메서드
     *
     * @param ingredientId 삭제한 재료 id
     */
    void deleteByIngredientId(Long ingredientId);
}
