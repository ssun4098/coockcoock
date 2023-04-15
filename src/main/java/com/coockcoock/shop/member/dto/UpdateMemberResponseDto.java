package com.coockcoock.shop.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

/**
 * 회원 정보 수정 응답 Dto
 *
 * @version 1.0
 * @since 23-03-25
 */
@Getter
@AllArgsConstructor
public class UpdateMemberResponseDto {
    private LocalDate updateDate;
}
