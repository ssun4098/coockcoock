package com.coockcoock.shop.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 회원 생성 요청 Dto
 *
 * @version 1.0
 * @since 23-03-25
 */
@AllArgsConstructor
@Getter
public class CreateMemberRequestDto {
    @NotNull
    @NotBlank
    @Size(min = 2, message = "로그인 아이디가 너무 짧습니다.")
    @Size(max = 32, message = "로그인 아이디는 최대 32자까지 허용합니다.")
    private String loginId;

    @NotNull
    @NotBlank
    @Size(min = 2, message = "비밀번호가 너무 짧습니다.")
    @Size(max = 32, message = "비밀번호는 최대 32자까지 허용합니다.")
    private String password;
}
