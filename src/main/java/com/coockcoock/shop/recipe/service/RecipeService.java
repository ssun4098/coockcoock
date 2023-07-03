package com.coockcoock.shop.recipe.service;

import com.coockcoock.shop.recipe.dto.*;
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

    /**
     * 게시물 리스트 반환
     *
     * @param requestDto 찾는 게시물의 제목
     * @param pageable 페이지 정보
     * @return 찾은 게시물 리스트
     */
    Page<RecipeFindResponseDto> findsRecipe(RecipeListRequestDto requestDto, Pageable pageable);


    RecipeDetailResponseDto findRecipeById(Long id);
}
