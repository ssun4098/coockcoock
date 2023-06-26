package com.coockcoock.shop.foodIngredient.service;

import com.coockcoock.shop.foodIngredient.dto.FoodIngredientRequestDto;
import com.coockcoock.shop.foodIngredient.entity.FoodIngredient;
import com.coockcoock.shop.ingredient.entity.Ingredient;
import com.coockcoock.shop.recipe.entity.Recipe;

import java.util.List;

/**
 * food ingredient service interface
 *
 * @since 23-06-26
 */
public interface FoodIngredientService {

    /**
     * 생성 메서드
     *
     * @param recipe 생성한 레시피
     * @param ingredientIds 사용한 재료들
     */
    void save(Recipe recipe, List<Ingredient> ingredientIds, List<String> amounts);

    /**
     * 업데이트 메서드
     *
     * @param recipe 업데이트할 레시피 id
     * @param ingredientIds 사용할 재료들
     */
    void update(Recipe recipe, List<Ingredient> ingredientIds, List<String> amounts);

    /**
     * 레시피 id를 통해 사용한 재료들을 메소드
     *
     * @param recipeId 삭제할 레시피 id
     */
    void deleteByRecipeId(Long recipeId);

    /**
     * 재료 id를 이용해 삭제 메소드
     *
     * @param ingredientId 삭제할 재료 id
     */
    void deleteByIngredientId(Long ingredientId);

    /**
     * 레시피 id를 통한 사용한 재료 조회 메소드
     *
     * @param recipeId 조회할 레시피 id
     * @return 사용한 재료 dto 리스트
     */
    List<FoodIngredientRequestDto> findByRecipeId(Long recipeId);
}
