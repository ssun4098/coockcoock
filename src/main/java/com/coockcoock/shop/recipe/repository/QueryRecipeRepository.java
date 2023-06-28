package com.coockcoock.shop.recipe.repository;

import com.coockcoock.shop.recipe.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * 게시물 Read 레포지터리
 *
 * @since 23-06-28
 */
public interface QueryRecipeRepository {

    /**
     * 검색 메서드
     *
     * @param name 검색할 이름
     * @param pageable 페이지 정보
     * @return 찾은 레시피 페이지
     */
    Page<Recipe> findsByName(String name, Pageable pageable);

    /**
     * 레시피 상세 정보 조회 메서드
     *
     * @param id 찾을 레시피의 id
     * @return Optional한 해당 레시피
     */
    Optional<Recipe> findById(Long id);
}
