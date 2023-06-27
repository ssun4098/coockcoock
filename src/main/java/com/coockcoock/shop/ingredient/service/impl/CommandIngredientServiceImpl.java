package com.coockcoock.shop.ingredient.service.impl;

import com.coockcoock.shop.foodIngredient.service.FoodIngredientService;
import com.coockcoock.shop.ingredient.dto.IngredientCreateRequestDto;
import com.coockcoock.shop.ingredient.dto.IngredientCreateResponseDto;
import com.coockcoock.shop.ingredient.dto.IngredientDeleteRequestDto;
import com.coockcoock.shop.ingredient.entity.Ingredient;
import com.coockcoock.shop.ingredient.repository.CommandIngredientRepository;
import com.coockcoock.shop.ingredient.service.CommandIngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CommandIngredientServiceImpl implements CommandIngredientService {
    private final CommandIngredientRepository commandIngredientRepository;
    private final FoodIngredientService foodIngredientService;

    /**
     * {@inheritDoc}
     */
    @Override
    public IngredientCreateResponseDto create(IngredientCreateRequestDto requestDto) {
        commandIngredientRepository.save(Ingredient.builder()
                .name(requestDto.getName())
                .build());
        return new IngredientCreateResponseDto(LocalDateTime.now());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(IngredientDeleteRequestDto requestDto) {
        foodIngredientService.deleteByIngredientId(requestDto.getId());
        commandIngredientRepository.deleteById(requestDto.getId());
    }
}
