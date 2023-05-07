package com.coockcoock.shop.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 로그인 요청 DTO
 *
 * @since 23-04-28
 */
@Getter
@AllArgsConstructor
public class LoginRequestDto {
    private final String loginId;
    private final String password;
}
