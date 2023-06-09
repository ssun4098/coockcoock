package com.coockcoock.shop.ingredient.controller;

import com.coockcoock.shop.common.dto.CommonResponseDto;
import com.coockcoock.shop.ingredient.dto.*;
import com.coockcoock.shop.ingredient.service.CommandIngredientService;
import com.coockcoock.shop.ingredient.service.QueryIngredientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Create, Delete Ingredient Rest Controller
 *
 * @since 23-06-06
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/ingredients")
public class CommandIngredientController {
    private final CommandIngredientService commandIngredientService;
    private final QueryIngredientService queryIngredientService;

    /**
     * Ingredient find Controller Method
     *
     * @param requestDto Find Ingredient's Name
     * @param pageable Page Info
     * @return Ingredient Found
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CommonResponseDto<Page<IngredientFindResponseDto>> findByName(
            IngredientFindRequestDto requestDto,
            @PageableDefault Pageable pageable) {
        return CommonResponseDto.<Page<IngredientFindResponseDto>>builder()
                .success(true)
                .data(queryIngredientService.findByName(requestDto, pageable))
                .build();
    }

    /**
     * Ingredient Create Controller Method
     *
     * @param requestDto Ingredient's name
     * @return Create LocalDateTime
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponseDto<IngredientCreateResponseDto> create(@Valid @RequestBody IngredientCreateRequestDto requestDto) {
        log.info("Ingredient Create {}", requestDto.getName());
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
