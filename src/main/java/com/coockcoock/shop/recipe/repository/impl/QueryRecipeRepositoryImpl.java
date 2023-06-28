package com.coockcoock.shop.recipe.repository.impl;

import com.coockcoock.shop.ingredient.entity.Ingredient;
import com.coockcoock.shop.recipe.entity.Recipe;
import com.coockcoock.shop.recipe.entity.querydsl.QRecipe;
import com.coockcoock.shop.recipe.repository.QueryRecipeRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 게시물 레포지터리 구현체
 *
 * @since 23-06-28
 */
@RequiredArgsConstructor
@Repository
public class QueryRecipeRepositoryImpl implements QueryRecipeRepository {
    private final JPAQueryFactory jpaQueryFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Recipe> findsByName(String name, Pageable pageable) {
        QRecipe recipe = QRecipe.recipe;
        BooleanBuilder builder = new BooleanBuilder();

        if(Objects.nonNull(name)) {
            builder.and(recipe.title.contains(name));
        }

        List<Recipe> recipes = jpaQueryFactory
                .selectFrom(recipe)
                .where(builder)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();
        Long size = jpaQueryFactory
                .select(recipe.count())
                .from(recipe)
                .fetchOne();

        return new PageImpl<>(recipes, pageable, size.intValue());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Recipe> findById(Long id) {
        QRecipe recipe = QRecipe.recipe;
        return Optional.ofNullable(jpaQueryFactory
                .selectFrom(recipe)
                .where(recipe.id.eq(id))
                .fetchOne());
    }
}
