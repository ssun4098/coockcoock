package com.coockcoock.shop.recipe.repository;

import com.coockcoock.shop.recipe.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QueryRecipeRepository {
    Page<Recipe> findsByName(String name, Pageable pageable);
}
