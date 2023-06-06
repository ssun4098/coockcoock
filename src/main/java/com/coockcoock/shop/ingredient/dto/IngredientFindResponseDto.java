package com.coockcoock.shop.ingredient.dto;

import com.coockcoock.shop.ingredient.entity.Ingredient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Ingredient Find Response Dto
 *
 * @since 23-06-06
 */
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class IngredientFindResponseDto {
    private Long id;
    private String name;

    /**
     * Entity to Dto method
     *
     * @param ingredient Entity
     * @return ResponseDto
     */
    public static IngredientFindResponseDto toDto(Ingredient ingredient) {
        return IngredientFindResponseDto.builder()
                .id(ingredient.getId())
                .name(ingredient.getName())
                .build();
    }
}
