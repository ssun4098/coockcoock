package com.coockcoock.shop.recipe.service.impl;

import com.coockcoock.shop.foodIngredient.service.FoodIngredientService;
import com.coockcoock.shop.ingredient.service.QueryIngredientService;
import com.coockcoock.shop.member.domain.Member;
import com.coockcoock.shop.member.exception.MemberNotFoundException;
import com.coockcoock.shop.member.repository.QueryMemberRepository;
import com.coockcoock.shop.recipe.dto.RecipeCreateRequestDto;
import com.coockcoock.shop.recipe.dto.RecipeCreateResponseDto;
import com.coockcoock.shop.recipe.dto.RecipeListRequestDto;
import com.coockcoock.shop.recipe.dto.RecipeFindResponseDto;
import com.coockcoock.shop.recipe.entity.Recipe;
import com.coockcoock.shop.recipe.repository.QueryRecipeRepository;
import com.coockcoock.shop.recipe.repository.RecipeRepository;
import com.coockcoock.shop.recipe.service.RecipeService;
import com.coockcoock.shop.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final QueryMemberRepository queryMemberRepository;
    private final QueryIngredientService queryIngredientService;
    private final FoodIngredientService foodIngredientService;
    private final QueryRecipeRepository queryRecipeRepository;
    private final JwtUtil jwtUtil;

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public RecipeCreateResponseDto create(RecipeCreateRequestDto requestDto, String token) {
        Member member = queryMemberRepository.findMemberByLoginId(jwtUtil.getLoginId(token.split(" ")[1].trim())).orElseThrow(
                () -> new MemberNotFoundException(jwtUtil.getLoginId(token))
        );

        Recipe recipe = Recipe.builder()
                .title(requestDto.getTitle())
                .cookery(requestDto.getCookery())
                .member(member)
                .build();

        recipeRepository.save(recipe);

        foodIngredientService.save(recipe, queryIngredientService.findsById(requestDto.getIngredientList()), requestDto.getAmounts());

        return new RecipeCreateResponseDto(recipe.getCreatedAt());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RecipeFindResponseDto> findsRecipe(RecipeListRequestDto requestDto, Pageable pageable) {
        return queryRecipeRepository.findsByName(requestDto.getTitle(), pageable)
                .map(recipe -> new RecipeFindResponseDto(recipe.getTitle(), recipe.getMember().getLoginId(), recipe.getCreatedAt()));
    }
}
