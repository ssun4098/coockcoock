package com.coockcoock.shop.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 로그인 아이디 중복 여부 반환 DTO
 */
@Getter
@AllArgsConstructor
public class CheckLoginIdResponseDto {
    private boolean check;
}
