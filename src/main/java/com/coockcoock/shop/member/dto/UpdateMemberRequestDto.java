package com.coockcoock.shop.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 회원 정보 수정 요청 Dto
 *
 * @version 1.0
 * @since 23-03-25
 */
@Getter
@AllArgsConstructor
public class UpdateMemberRequestDto {
    private String loginId;
    private String password;
}
