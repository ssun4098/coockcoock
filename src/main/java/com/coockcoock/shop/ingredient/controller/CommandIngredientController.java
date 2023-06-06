package com.coockcoock.shop.ingredient.controller;

import com.coockcoock.shop.common.dto.CommonResponseDto;
import com.coockcoock.shop.ingredient.dto.IngredientCreateRequestDto;
import com.coockcoock.shop.ingredient.dto.IngredientCreateResponseDto;
import com.coockcoock.shop.ingredient.dto.IngredientDeleteRequestDto;
import com.coockcoock.shop.ingredient.service.CommandIngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Create, Delete Ingredient Rest Controller
 *
 * @since 23-06-06
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/ingredients")
public class CommandIngredientController {
    private final CommandIngredientService commandIngredientService;

    /**
     * Ingredient Create Controller Method
     *
     * @param requestDto Ingredient's name
     * @return Create LocalDateTime
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponseDto<IngredientCreateResponseDto> create(@Valid @RequestBody IngredientCreateRequestDto requestDto) {
        return CommonResponseDto.<IngredientCreateResponseDto>builder()
                .success(true)
                .data(commandIngredientService.create(requestDto))
                .build();
    }

    /**
     * Ingredient Delete Controller Method
     *
     * @param requestDto Ingredient's id
     * @return void
     */
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public CommonResponseDto<Void> delete(@RequestBody IngredientDeleteRequestDto requestDto) {
        commandIngredientService.deleteById(requestDto);
        return CommonResponseDto.<Void>builder()
                .success(true)
                .build();
    }
}
