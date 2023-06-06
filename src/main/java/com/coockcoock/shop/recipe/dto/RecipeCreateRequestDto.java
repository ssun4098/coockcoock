package com.coockcoock.shop.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

/**
 * 게시물 생성 요청 DTO
 *
 * @since 23-06-03
 */
@Getter
@AllArgsConstructor
public class RecipeCreateRequestDto {
    private String title;
    private String cookery;
    private String loginId;
    private MultipartFile[] upLoadFile;
}
