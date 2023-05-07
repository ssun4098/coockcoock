package com.coockcoock.shop.member.repository.impl;

import static org.assertj.core.api.Assertions.assertThat;

import com.coockcoock.shop.member.domain.Grade;
import com.coockcoock.shop.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Transactional
@SpringBootTest
class QueryDslMemberRepositoryTest {
    @Autowired
    private QueryDslMemberRepository queryDslMemberRepository;
    @PersistenceContext
    private EntityManager entityManager;
    private Member member;
    private Grade grade;

    @BeforeEach
    void setUp() {
        grade = Grade.builder().name("grade").build();
        entityManager.persist(grade);

        member = Member
                .builder()
                .loginId("loginId")
                .password("password")
                .signUpDate(LocalDate.now())
                .ban(false)
                .banReason(null)
                .grade(grade)
                .build();
    }
    @Test
    @DisplayName("로그인 아이디가 존재할 경우 True 반환하는 경우")
    void existsLoginIdReturnTrue() {
        //when
        entityManager.persist(member);

        //given
        boolean result = queryDslMemberRepository.existsLoginId(member.getLoginId());

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("로그인 아이디가 존재하지 않는 경우 False 반환하는 경우")
    void existsLoginIdReturnFalse() {
        //given, when
        boolean result = queryDslMemberRepository.existsLoginId("loginId");

        //then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("로그인 아이디로 회원을 찾을 경우 없는 회원일 경우")
    void findMemberByLoginIdReturnNull() {
        //given, when
        Optional<Member> result = queryDslMemberRepository.findMemberByLoginId("loginId");

        //then
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    @DisplayName("로그인 아이디로 회원을 찾을 경우 존재할 경우")
    void findMemberByLoginIdReturnMember() {
        //when
        entityManager.persist(member);

        //given
        Optional<Member> result = queryDslMemberRepository.findMemberByLoginId("loginId");

        //then
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getLoginId()).isEqualTo("loginId");
    }
}