package com.coockcoock.shop.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 게시물 생성 요청 DTO
 *
 * @since 23-06-03
 */
@Getter
@AllArgsConstructor
public class RecipeCreateRequestDto {
    @NotNull
    @NotBlank
    private String title;
    @NotNull
    @NotBlank
    private String cookery;
    @NotNull
    @NotBlank
    private String loginId;
    private MultipartFile[] upLoadFile;
    private List<Long> ingredientList;
}
