package com.coockcoock.shop.member.repository;

import com.coockcoock.shop.member.domain.Member;

import java.util.Optional;

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

    /**
     * 로그인 아이디로 회원을 찾는 메서드
     *
     * @param loginId 찾을 회원의 로그인 아이디
     * @return 해당 loginId 회원
     * @since 23-04-15
     */
    Optional<Member> findMemberByLoginId(String loginId);
}
