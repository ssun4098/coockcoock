package com.coockcoock.shop.foodIngredient.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FoodIngredientRequestDto {
    private String ingredientAmount;
    private String ingredientName;
}
