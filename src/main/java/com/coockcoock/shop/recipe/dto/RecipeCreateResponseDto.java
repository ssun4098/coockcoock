package com.coockcoock.shop.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeCreateResponseDto {
    private LocalDateTime createAt;
}
