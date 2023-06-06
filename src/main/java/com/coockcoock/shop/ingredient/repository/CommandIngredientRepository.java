package com.coockcoock.shop.ingredient.repository;

import com.coockcoock.shop.ingredient.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Ingredient Jpa Repository
 *
 * @since 23-06-06
 */
public interface CommandIngredientRepository extends JpaRepository<Ingredient, Long> {
}
