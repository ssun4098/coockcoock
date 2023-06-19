package com.coockcoock.shop.recipe.service.impl;

import com.coockcoock.shop.member.domain.Member;
import com.coockcoock.shop.member.exception.MemberNotFoundException;
import com.coockcoock.shop.member.repository.QueryMemberRepository;
import com.coockcoock.shop.recipe.dto.RecipeCreateRequestDto;
import com.coockcoock.shop.recipe.dto.RecipeCreateResponseDto;
import com.coockcoock.shop.recipe.entity.Recipe;
import com.coockcoock.shop.recipe.repository.RecipeRepository;
import com.coockcoock.shop.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final QueryMemberRepository queryMemberRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public RecipeCreateResponseDto create(RecipeCreateRequestDto requestDto) {
        Member member = queryMemberRepository.findMemberByLoginId(requestDto.getLoginId()).orElseThrow(
                () -> new MemberNotFoundException(requestDto.getLoginId())
        );

        Recipe recipe = Recipe.builder()
                .title(requestDto.getTitle())
                .cookery(requestDto.getCookery())
                .member(member)
                .build();

        recipeRepository.save(recipe);

        return null;
    }
}
