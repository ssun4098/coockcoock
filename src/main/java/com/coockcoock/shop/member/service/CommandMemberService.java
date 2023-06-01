package com.coockcoock.shop.member.service;

import com.coockcoock.shop.member.dto.*;

import javax.servlet.http.Cookie;

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
    CreateMemberResponseDto create(CreateMemberRequestDto requestDto);

    /**
     * 회원 업데이터 메서드
     *
     * @param requestDto 회원 업데이트 요청 dto
     * @return 회원 업데이트 응답 dto
     */
    UpdateMemberResponseDto update(UpdateMemberRequestDto requestDto);

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

    /**
     * 로그인 메서드
     *
     * @param requestDto 로그인 요청 DTO
     * @return JWT 토큰
     * @since 23-04-28
     */
    String login(LoginRequestDto requestDto);

    /**
     * 로그아웃 메서드
     * Refresh 토큰 삭제와 기존 토큰은 블랙리스트에 추가한다.
     *
     * @param loginId 로그아웃할 LoginId
     * @since 23-05-15
     */
    void logout(String loginId, String token);

    /**
     * JWT Token 블랙리스트를 확인하는 메서드
     *
     * @param token 확인할 토큰
     * @return If Token exists True else False
     */
    boolean checkBlackList(String token);
}
