package com.coockcoock.shop.recipe.service;

import com.coockcoock.shop.recipe.dto.RecipeCreateResponseDto;
import com.coockcoock.shop.recipe.dto.RecipeCreateRequestDto;
import com.coockcoock.shop.recipe.dto.RecipeListRequestDto;
import com.coockcoock.shop.recipe.dto.RecipeFindResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 게시물 서비스 인터페이스
 *
 * @since 23-06-28
 */
public interface RecipeService {

    /**
     * 게시물 생성 메서드
     *
     * @param requestDto
     * @param token
     * @return
     */
    RecipeCreateResponseDto create(RecipeCreateRequestDto requestDto, String token);

    Page<RecipeFindResponseDto> findsRecipe(RecipeListRequestDto requestDto, Pageable pageable);


}
