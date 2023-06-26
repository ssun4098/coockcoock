package com.coockcoock.shop.ingredient.service.impl;

import com.coockcoock.shop.ingredient.dto.IngredientFindRequestDto;
import com.coockcoock.shop.ingredient.dto.IngredientFindResponseDto;
import com.coockcoock.shop.ingredient.entity.Ingredient;
import com.coockcoock.shop.ingredient.repository.QueryIngredientRepository;
import com.coockcoock.shop.ingredient.service.QueryIngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Query Ingredient Service Impl
 *
 * @since 23-06-06
 */
@RequiredArgsConstructor
@Service
public class QueryIngredientServiceImpl implements QueryIngredientService {
    private final QueryIngredientRepository queryIngredientRepository;
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<IngredientFindResponseDto> findByName(IngredientFindRequestDto requestDto, Pageable pageable) {
        Page<Ingredient> ingredients = queryIngredientRepository.findByName(requestDto.getName(), pageable);
        return new PageImpl<>(
                ingredients.getContent().stream()
                        .map(IngredientFindResponseDto::toDto)
                        .collect(Collectors.toList()),
                pageable,
                ingredients.getTotalElements()
        );
    }

    @Override
    public List<Ingredient> findsById(List<Long> ids) {
        return queryIngredientRepository.findsById(ids);
    }
}
