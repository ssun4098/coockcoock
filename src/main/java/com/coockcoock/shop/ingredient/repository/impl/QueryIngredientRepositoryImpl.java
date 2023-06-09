package com.coockcoock.shop.ingredient.repository.impl;

import com.coockcoock.shop.ingredient.entity.Ingredient;
import com.coockcoock.shop.ingredient.entity.querydsl.QIngredient;
import com.coockcoock.shop.ingredient.repository.QueryIngredientRepository;
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

@RequiredArgsConstructor
@Repository
public class QueryIngredientRepositoryImpl implements QueryIngredientRepository {
    private final JPAQueryFactory jpaQueryFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Ingredient> findByName(String name, Pageable pageable) {
        QIngredient ingredient = QIngredient.ingredient;
        BooleanBuilder builder = new BooleanBuilder();

        if(Objects.nonNull(name)) {
            builder.and(ingredient.name.contains(name));
        }
        List<Ingredient> ingredients = jpaQueryFactory
                .selectFrom(ingredient)
                .where(builder)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        return new PageImpl<>(ingredients, pageable, ingredients.size());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Ingredient> findById(Long id) {
        QIngredient ingredient = QIngredient.ingredient;
        return jpaQueryFactory
                .selectFrom(ingredient)
                .where(ingredient.id.eq(id))
                .stream()
                .findFirst();
    }
}
