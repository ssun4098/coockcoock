package com.coockcoock.shop.foodIngredient.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class FoodIngredientId implements Serializable {
    private Long recipe;
    private Long ingredient;
}
