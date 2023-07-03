package com.coockcoock.shop.recipe.service.impl;

import com.coockcoock.shop.foodIngredient.service.FoodIngredientService;
import com.coockcoock.shop.ingredient.service.QueryIngredientService;
import com.coockcoock.shop.member.domain.Member;
import com.coockcoock.shop.member.exception.MemberNotFoundException;
import com.coockcoock.shop.member.repository.QueryMemberRepository;
import com.coockcoock.shop.recipe.dto.*;
import com.coockcoock.shop.recipe.entity.Recipe;
import com.coockcoock.shop.recipe.exception.RecipeNotFoundException;
import com.coockcoock.shop.recipe.repository.QueryRecipeRepository;
import com.coockcoock.shop.recipe.repository.RecipeRepository;
import com.coockcoock.shop.recipe.service.RecipeService;
import com.coockcoock.shop.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

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
    @Value("${file.path}")
    private String path;

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

        if(Objects.nonNull(requestDto.getIngredientList())) {
            foodIngredientService.save(recipe, queryIngredientService.findsById(requestDto.getIngredientList()), requestDto.getAmounts());
        }

        if(Objects.nonNull(requestDto.getUpLoadFile()) && requestDto.getUpLoadFile().size() > 0) {
            saveFile(requestDto.getUpLoadFile(), recipe.getId());
        }
        return new RecipeCreateResponseDto(recipe.getCreatedAt());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RecipeFindResponseDto> findsRecipe(RecipeListRequestDto requestDto, Pageable pageable) {
        return queryRecipeRepository.findsByName(requestDto.getTitle(), pageable)
                .map(recipe ->
                        new RecipeFindResponseDto(recipe.getId(), recipe.getTitle(), recipe.getMember().getLoginId(), recipe.getCreatedAt()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public RecipeDetailResponseDto findRecipeById(Long id) {
        return RecipeDetailResponseDto.fromEntity(queryRecipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException(id.toString())));
    }

    private void saveFile(List<MultipartFile> multipartFiles, Long id) {
        try {
            Path downloadPath = Paths.get(path + id);
            Files.createDirectory(downloadPath);

            // 파일 저장
            for (MultipartFile file : multipartFiles) {
                if (Objects.requireNonNull(file.getContentType()).startsWith("image")) {
                    String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
                    String extension = StringUtils.getFilenameExtension(filename);
                    String storedFilename = id + "." + extension;
                    Path filePath = downloadPath.resolve(storedFilename);
                    file.transferTo(filePath.toFile());
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
