package com.coockcoock.shop.member.repository;

import com.coockcoock.shop.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA를 이용한 회원 레포지터리
 *
 * @version 1.0
 * @since 23-03-25
 */
public interface CommonMemberRepository extends JpaRepository<Member, Long> {
}
