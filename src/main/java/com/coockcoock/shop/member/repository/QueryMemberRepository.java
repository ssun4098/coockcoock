package com.coockcoock.shop.member.repository;

/**
 * 회원 조회 관련 Repository 인터페이스
 *
 * @version 1.0
 * @since 23-03-25
 */
public interface QueryMemberRepository {

    /**
     * 로그인 아이디 중복 여부 확인 메서드
     *
     * @param LoginId 로그인 아이디
     * @return 사용중인 로그인 아이디면 true, 아니면 false
     * @since 23-03-25
     */
    boolean existsLoginId(String LoginId);
}
