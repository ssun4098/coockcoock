package com.coockcoock.shop.ingredient.service;

import com.coockcoock.shop.ingredient.dto.IngredientFindRequestDto;
import com.coockcoock.shop.ingredient.dto.IngredientFindResponseDto;
import com.coockcoock.shop.ingredient.entity.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Query Ingredient Service Interface
 *
 * @since 23-06-06
 */
public interface QueryIngredientService {

    /**
     * Ingredient Find By Name Method
     *
     * @param requestDto Name
     * @return Response Page List
     * @since 23-06-06
     */
    Page<IngredientFindResponseDto> findByName(IngredientFindRequestDto requestDto, Pageable pageable);

    /**
     * 아이디 리스트로 조회하는 메소드
     *
     * @param ids 조회할 id 리스트
     * @return 조회된 아이디 리스트
     */
    List<Ingredient> findsById(List<Long> ids);
}
