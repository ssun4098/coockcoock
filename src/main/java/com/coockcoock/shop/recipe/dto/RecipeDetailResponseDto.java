package com.coockcoock.shop.recipe.dto;

import com.coockcoock.shop.foodIngredient.dto.FoodIngredientRequestDto;
import com.coockcoock.shop.foodIngredient.entity.FoodIngredient;
import com.coockcoock.shop.ingredient.dto.IngredientDeleteRequestDto;
import com.coockcoock.shop.ingredient.dto.IngredientFindResponseDto;
import com.coockcoock.shop.ingredient.entity.Ingredient;
import com.coockcoock.shop.recipe.entity.Recipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class RecipeDetailResponseDto {
    private Long id;
    private String title;
    private String cookery;
    private String writer;
    private LocalDateTime createAt;
    private List<FoodIngredientRequestDto> ingredients;

    public static RecipeDetailResponseDto fromEntity(Recipe recipe, List<FoodIngredientRequestDto> ingredients) {
        return RecipeDetailResponseDto.builder()
                .id(recipe.getId())
                .title(recipe.getTitle())
                .cookery(recipe.getCookery())
                .writer(recipe.getMember().getLoginId())
                .createAt(recipe.getCreatedAt())
                .ingredients(ingredients)
                .build();
    }
}
