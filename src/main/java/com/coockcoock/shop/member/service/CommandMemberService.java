package com.coockcoock.shop.member.service;

import com.coockcoock.shop.member.dto.*;

import javax.security.auth.login.LoginException;

/**
 * 회원 생성, 업데이터, 삭제 Service 인터페이스
 *
 * @version 1.0
 * @since 23-03-25
 */
public interface CommandMemberService {

    /**
     * 회원 생성 메서드
     *
     * @param requestDto 회원 생성 요청 dto
     * @return 회원 생성 응답 dto(생성 날짜)
     * @since 23-03-25
     */
    CreateMemberResponseDto create(CreateMemberRequestDto requestDto) throws LoginException;

    /**
     * 회원 업데이터 메서드
     *
     * @param requestDto 회원 업데이트 요청 dto
     * @return 회원 업데이트 응답 dto
     */
    UpdateMemberResponseDto update(UpdateMemberRequestDto requestDto) throws LoginException;

    /**
     * LoginId를 이용한 논리적 회원 삭제
     *
     * @param loginId 삭제할 회원의 loginId
     * @since 23-03-25
     */
    void logicalDelete(String loginId);

    /**
     * 로그인 아이디 중복 확인 메서드
     *
     * @param loginId 확인할 loginId
     * @return 중복 여부 DTO
     * @since 23-04-24
     */
    CheckLoginIdResponseDto checkLoginId(String loginId);
}
