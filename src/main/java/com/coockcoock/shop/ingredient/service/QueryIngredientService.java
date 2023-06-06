package com.coockcoock.shop.ingredient.service;

import com.coockcoock.shop.ingredient.dto.IngredientFindRequestDto;
import com.coockcoock.shop.ingredient.dto.IngredientFindResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Query Ingredient Service Interface
 *
 * @since 23-06-06
 */
public interface QueryIngredientService {

    /**
     * Ingredient Find By Name Method
     *
     * @param requestDto Name
     * @return Response Page List
     * @since 23-06-06
     */
    Page<IngredientFindResponseDto> findByName(IngredientFindRequestDto requestDto, Pageable pageable);
}
