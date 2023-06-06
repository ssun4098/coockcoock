package com.coockcoock.shop.ingredient.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientCreateRequestDto {
    @NotNull
    @NotBlank
    private String name;

}
