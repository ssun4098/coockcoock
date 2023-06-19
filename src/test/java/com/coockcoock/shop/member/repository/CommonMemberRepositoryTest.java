package com.coockcoock.shop.member.repository;

import com.coockcoock.shop.member.domain.Grade;
import com.coockcoock.shop.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CommonMemberRepositoryTest {

    @Autowired
    private CommonMemberRepository commonMemberRepository;

    @Test
    public void create() {
        LocalDate localDate = LocalDate.now();
        Member member = Member.builder()
                .loginId("loginId")
                .password("password")
                .signUpDate(localDate)
                .grade(new Grade(1L, "default"))
                .build();

        Member result = commonMemberRepository.save(member);

        assertThat(result.getLoginId()).isEqualTo(member.getLoginId());
    }
}