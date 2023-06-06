package com.coockcoock.shop.recipe.repository;

import com.coockcoock.shop.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 게시물 Jpa 레포지터리
 *
 * @since 23-06-03
 */
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
