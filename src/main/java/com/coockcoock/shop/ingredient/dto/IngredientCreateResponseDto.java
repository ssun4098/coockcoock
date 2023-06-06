package com.coockcoock.shop.ingredient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Ingredient Response Dto
 *
 * @since 23-06-06
 */
@Getter
@AllArgsConstructor
public class IngredientCreateResponseDto {
    LocalDateTime createDate;
}
