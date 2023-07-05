package com.coockcoock.shop.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.servlet.http.Cookie;

/**
 * 로그인 응답 DTO
 */
@Getter
@AllArgsConstructor
public class LoginResponseDto {
    private Cookie cookie;
}
