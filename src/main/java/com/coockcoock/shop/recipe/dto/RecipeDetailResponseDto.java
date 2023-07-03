package com.coockcoock.shop.recipe.dto;

import com.coockcoock.shop.recipe.entity.Recipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class RecipeDetailResponseDto {
    private Long id;
    private String title;
    private String cookery;
    private String writer;
    private LocalDateTime createAt;

    public static RecipeDetailResponseDto fromEntity(Recipe recipe) {
        return RecipeDetailResponseDto.builder()
                .id(recipe.getId())
                .title(recipe.getTitle())
                .cookery(recipe.getCookery())
                .writer(recipe.getMember().getLoginId())
                .createAt(recipe.getCreatedAt())
                .build();
    }
}
