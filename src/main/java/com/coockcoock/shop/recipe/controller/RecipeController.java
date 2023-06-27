package com.coockcoock.shop.recipe.controller;

import com.coockcoock.shop.common.dto.CommonResponseDto;
import com.coockcoock.shop.recipe.dto.RecipeCreateRequestDto;
import com.coockcoock.shop.recipe.dto.RecipeCreateResponseDto;
import com.coockcoock.shop.recipe.dto.RecipeFindResponseDto;
import com.coockcoock.shop.recipe.dto.RecipeListRequestDto;
import com.coockcoock.shop.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    @PostMapping
    public CommonResponseDto<RecipeCreateResponseDto> create(@RequestBody RecipeCreateRequestDto requestDto, HttpServletRequest request) {
        return CommonResponseDto.<RecipeCreateResponseDto>builder()
                .success(true)
                .data(recipeService.create(requestDto, request.getHeader(HttpHeaders.AUTHORIZATION)))
                .build();
    }

    @GetMapping
    public CommonResponseDto<Page<RecipeFindResponseDto>> find(@RequestBody RecipeListRequestDto requestDto, @PageableDefault Pageable pageable) {
        return CommonResponseDto.<Page<RecipeFindResponseDto>>builder()
                .success(true)
                .data((recipeService.findsRecipe(requestDto, pageable)))
                .build();
    }
}
