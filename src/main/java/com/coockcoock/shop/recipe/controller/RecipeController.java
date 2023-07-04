package com.coockcoock.shop.recipe.controller;

import com.coockcoock.shop.common.dto.CommonResponseDto;
import com.coockcoock.shop.recipe.dto.*;
import com.coockcoock.shop.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    @PostMapping
    public CommonResponseDto<RecipeCreateResponseDto> create(@ModelAttribute RecipeCreateRequestDto requestDto, HttpServletRequest request) {
        return CommonResponseDto.<RecipeCreateResponseDto>builder()
                .success(true)
                .data(recipeService.create(requestDto, request.getHeader(HttpHeaders.AUTHORIZATION)))
                .build();
    }

    @PostMapping("/{id}")
    public CommonResponseDto<RecipeUpdateResponseDto> update(
            @PathVariable(name = "id")Long id,
            @ModelAttribute RecipeUpdateRequestDto requestDto,
            HttpServletRequest request) {
        return CommonResponseDto.<RecipeUpdateResponseDto>builder()
                .success(true)
                .data(recipeService.update(id, requestDto, request.getHeader(HttpHeaders.AUTHORIZATION)))
                .build();
    }

    @GetMapping
    public CommonResponseDto<Page<RecipeFindResponseDto>> finds(RecipeListRequestDto requestDto, @PageableDefault Pageable pageable) {
        return CommonResponseDto.<Page<RecipeFindResponseDto>>builder()
                .success(true)
                .data((recipeService.findsRecipe(requestDto, pageable)))
                .build();
    }

    @GetMapping("/{id}")
    public CommonResponseDto<RecipeDetailResponseDto> find(@PathVariable(name = "id")Long id) {
        return CommonResponseDto.<RecipeDetailResponseDto>builder()
                .success(true)
                .data(recipeService.findRecipeById(id))
                .errorDto(null)
                .build();
    }

    @DeleteMapping("/{id}")
    public CommonResponseDto<Void> delete(@PathVariable(name = "id")Long id, HttpServletRequest request) {
        recipeService.delete(id, request.getHeader(HttpHeaders.AUTHORIZATION));
        return CommonResponseDto.<Void>builder()
                .success(true)
                .build();
    }
}
