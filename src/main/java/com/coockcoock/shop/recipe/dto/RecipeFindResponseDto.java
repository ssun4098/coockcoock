package com.coockcoock.shop.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeFindResponseDto {
    String title;
    String writer;
    LocalDateTime createAt;
}
