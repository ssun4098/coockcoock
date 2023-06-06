package com.coockcoock.shop.ingredient.repository;

import com.coockcoock.shop.ingredient.entity.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Ingredient Query Repository
 *
 * @since 23-06-06
 */
public interface QueryIngredientRepository {
    /**
     * 해당 name이 포함되는 ingredient를 찾는 메서드
     *
     * @param name 찾을 이름
     * @param pageable 페이지 정보
     * @return 찾은 결과
     */
    Page<Ingredient> findByName(String name, Pageable pageable);

    /**
     * Id로 ingredient를 찾는 메서드
     *
     * @param id 찾을 id
     * @return 결과
     */
    Optional<Ingredient> findById(Long id);
}
