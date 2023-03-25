package com.coockcoock.shop.member.repository.impl;

import static org.assertj.core.api.Assertions.assertThat;

import com.coockcoock.shop.member.domain.Grade;
import com.coockcoock.shop.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
@SpringBootTest
class QueryDslMemberRepositoryTest {
    @Autowired
    private QueryDslMemberRepository queryDslMemberRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @Test
    @DisplayName("로그인 아이디가 존재할 경우 True 반환하는 경우")
    void existsLoginIdReturnTrue() {
        Grade grade = Grade.builder().name("grade").build();
        entityManager.persist(grade);

        Member member = Member
                .builder()
                .loginId("loginId")
                .password("password")
                .signUpDate(LocalDate.now())
                .ban(false)
                .banReason(null)
                .grade(grade)
                .build();
        entityManager.persist(member);

        boolean result = queryDslMemberRepository.existsLoginId(member.getLoginId());
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("로그인 아이디가 존재하지 않는 경우 False 반환하는 경우")
    void existsLoginIdReturnFalse() {
        boolean result = queryDslMemberRepository.existsLoginId("loginId");
        assertThat(result).isFalse();
    }
}