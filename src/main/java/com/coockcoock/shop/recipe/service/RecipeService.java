package com.coockcoock.shop.recipe.service;

import com.coockcoock.shop.recipe.dto.RecipeCreateResponseDto;
import com.coockcoock.shop.recipe.dto.RecipeCreateRequestDto;

/**
 * 게시물 서비스 인터페이스
 *
 * @since 23-06-03
 */
public interface RecipeService {

    RecipeCreateResponseDto create(RecipeCreateRequestDto requestDto);
}
