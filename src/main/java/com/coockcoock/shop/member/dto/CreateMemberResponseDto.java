package com.coockcoock.shop.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

/**
 * 회원 생성 응답 Dto
 *
 * @version 1.0
 * @since 23-03-25
 */
@Getter
@AllArgsConstructor
public class CreateMemberResponseDto {
    private LocalDate signUpDate;
}
