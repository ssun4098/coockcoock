package com.coockcoock.shop.member.repository.impl;

import com.coockcoock.shop.member.domain.Member;
import com.coockcoock.shop.member.domain.querydsl.QMember;
import com.coockcoock.shop.member.repository.QueryMemberRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 회원 조회 관련 Repository 구현체
 *
 * @version 1.0
 * @since 23-03-25
 */
@RequiredArgsConstructor
@Repository
public class QueryDslMemberRepository implements QueryMemberRepository {
    private final JPAQueryFactory queryFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsLoginId(String loginId) {
        QMember member = QMember.member;

        return Optional
                .ofNullable(queryFactory.selectFrom(member)
                        .where(member.loginId.eq(loginId).and(member.withdrawal.isFalse()))
                        .fetchFirst())
                .isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Member> findMemberByLoginId(String loginId) {
        QMember member = QMember.member;

        return Optional.ofNullable(queryFactory.selectFrom(member)
                .where(member.loginId.eq(loginId).and(member.withdrawal.isFalse()))
                .fetchFirst());
    }
}
