package com.coockcoock.shop.ingredient.service;

import com.coockcoock.shop.ingredient.dto.IngredientCreateRequestDto;
import com.coockcoock.shop.ingredient.dto.IngredientCreateResponseDto;
import com.coockcoock.shop.ingredient.dto.IngredientDeleteRequestDto;

/**
 * Ingredient CUD Service Interface
 *
 * @since 23-06-06
 */
public interface CommandIngredientService {
    /**
     * Ingredient Create Method
     *
     * @param requestDto Ingredient Name
     * @return Create Time
     */
    IngredientCreateResponseDto create(IngredientCreateRequestDto requestDto);

    /**
     * Ingredient Delete Method
     *
     * @param requestDto Ingredient Id
     */
    void deleteById(IngredientDeleteRequestDto requestDto);
}
