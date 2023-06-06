package com.coockcoock.shop.ingredient.service.impl;

import com.coockcoock.shop.ingredient.dto.IngredientCreateRequestDto;
import com.coockcoock.shop.ingredient.dto.IngredientDeleteRequestDto;
import com.coockcoock.shop.ingredient.repository.CommandIngredientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

class CommandIngredientServiceImplTest {
    private CommandIngredientServiceImpl commandIngredientService;
    private CommandIngredientRepository commandIngredientRepository;

    @BeforeEach
    void setUp() {
        commandIngredientRepository = Mockito.mock(CommandIngredientRepository.class);
        commandIngredientService = new CommandIngredientServiceImpl(commandIngredientRepository);
    }

    @Test
    @DisplayName("생성 메서드 테스트")
    void createTest() {
        //given
        IngredientCreateRequestDto requestDto = new IngredientCreateRequestDto("name");

        //when
        commandIngredientService.create(requestDto);

        //then
        verify(commandIngredientRepository, atLeastOnce()).save(any());
    }

    @Test
    @DisplayName("삭제 메서드 테스트")
    void deleteByIdTest() {
        //given
        IngredientDeleteRequestDto requestDto = new IngredientDeleteRequestDto(1L);

        //when
        commandIngredientService.deleteById(requestDto);

        //then
        verify(commandIngredientRepository, atLeastOnce()).deleteById(1L);
    }
}