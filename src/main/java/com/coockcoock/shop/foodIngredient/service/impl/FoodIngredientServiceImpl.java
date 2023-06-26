package com.coockcoock.shop.foodIngredient.service.impl;

import com.coockcoock.shop.foodIngredient.dto.FoodIngredientRequestDto;
import com.coockcoock.shop.foodIngredient.entity.FoodIngredient;
import com.coockcoock.shop.foodIngredient.repositroy.CommonFoodIngredientRepository;
import com.coockcoock.shop.foodIngredient.repositroy.QueryFoodIngredientRepository;
import com.coockcoock.shop.foodIngredient.service.FoodIngredientService;
import com.coockcoock.shop.ingredient.entity.Ingredient;
import com.coockcoock.shop.recipe.entity.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Service
public class FoodIngredientServiceImpl implements FoodIngredientService {
    private final CommonFoodIngredientRepository commonFoodIngredientRepository;
    private final QueryFoodIngredientRepository queryFoodIngredientRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(Recipe recipe, List<Ingredient> ingredientIds, List<String> amounts) {
        List<FoodIngredient> foodIngredients = IntStream.range(0, ingredientIds.size())
                .mapToObj(i -> FoodIngredient.builder()
                        .recipe(recipe)
                        .ingredient(ingredientIds.get(i))
                        .amount(amounts.get(i))
                        .build())
                .collect(Collectors.toList());

        commonFoodIngredientRepository.saveAll(foodIngredients);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(Recipe recipe, List<Ingredient> ingredientIds, List<String> amounts) {
        queryFoodIngredientRepository.deleteByRecipeId(recipe.getId());
        List<FoodIngredient> foodIngredients = IntStream.range(0, ingredientIds.size())
                .mapToObj(i -> FoodIngredient.builder()
                        .recipe(recipe)
                        .ingredient(ingredientIds.get(i))
                        .amount(amounts.get(i))
                        .build())
                .collect(Collectors.toList());

        commonFoodIngredientRepository.saveAll(foodIngredients);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteByRecipeId(Long recipeId) {
        queryFoodIngredientRepository.deleteByRecipeId(recipeId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteByIngredientId(Long ingredientId) {
        queryFoodIngredientRepository.deleteByIngredientId(ingredientId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<FoodIngredientRequestDto> findByRecipeId(Long recipeId) {
        return queryFoodIngredientRepository
                .findByRecipeId(recipeId)
                .stream()
                .map(entity -> FoodIngredientRequestDto
                        .builder()
                        .ingredientName(entity.getIngredient().getName())
                        .ingredientAmount(entity.getAmount())
                        .build())
                .collect(Collectors.toList());
    }
}
