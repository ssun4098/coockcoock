package com.coockcoock.shop.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 로그인 아이디 중복 여부 확인 Request DTO
 *
 * @since 23-04-24
 */
@Getter
@AllArgsConstructor
public class CheckLoginIdRequestDto {
    @NotNull
    @NotBlank
    @Size(min = 2, message = "로그인 아이디가 너무 짧습니다.")
    @Size(max = 12, message = "로그인 아이디는 최대 12자까지 허용합니다.")
    private String loginId;
}
