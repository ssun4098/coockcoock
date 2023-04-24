package com.coockcoock.shop.member.service.impl;

import com.coockcoock.shop.member.domain.Member;
import com.coockcoock.shop.member.dto.*;
import com.coockcoock.shop.member.exception.LoginIdExistsException;
import com.coockcoock.shop.member.exception.MemberNotFoundException;
import com.coockcoock.shop.member.repository.CommonMemberRepository;
import com.coockcoock.shop.member.repository.impl.QueryDslMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

/**
 * CommandMemberServiceImpl 테스트 코드
 *
 * @version 1.0
 * @since 23-04-16
 */
class CommandMemberServiceImplTest {

    private CommandMemberServiceImpl commandMemberService;
    private CommonMemberRepository commonMemberRepository;
    private QueryDslMemberRepository queryDslMemberRepository;
    private CreateMemberRequestDto createMemberRequestDto;
    private UpdateMemberRequestDto updateMemberRequestDto;
    private Member member = Member.builder()
            .loginId("loginId")
            .password("password")
            .build();

    @BeforeEach
    void setUp() {
        commonMemberRepository = Mockito.mock(CommonMemberRepository.class);
        queryDslMemberRepository = Mockito.mock(QueryDslMemberRepository.class);
        PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
        commandMemberService = new CommandMemberServiceImpl(commonMemberRepository, queryDslMemberRepository, passwordEncoder);
        createMemberRequestDto = new CreateMemberRequestDto("loginId", "password");
        updateMemberRequestDto = new UpdateMemberRequestDto("loginId", "password");
    }

    @Test
    @DisplayName("회원 생성 시 로그인 아이디 중복으로 인한 LoginIdExistsException 발생")
    void createThrowsLoginIdExistsException() {
        //given
        Mockito.when(queryDslMemberRepository.existsLoginId("loginId")).thenReturn(true);

        //when, then
        assertThatThrownBy(() -> commandMemberService.create(createMemberRequestDto))
                .isInstanceOf(LoginIdExistsException.class);
    }

    @Test
    @DisplayName("회원 생성 성공")
    void createSuccess() throws Exception{
        //given
        Mockito.when(queryDslMemberRepository.existsLoginId("loginId")).thenReturn(false);

        //when
        CreateMemberResponseDto result = commandMemberService.create(createMemberRequestDto);

        //then
        verify(commonMemberRepository, atLeastOnce()).save(any());
    }
    @Test
    @DisplayName("회원 정보 변경 시 존재 하지않은 회원일 경우 MemberNotFoundException 발생")
    void updateThrowsMemberNotFoundException() {
        //given
        Mockito.when(queryDslMemberRepository.findMemberByLoginId("loginId")).thenReturn(Optional.empty());

        //when, //then
        assertThatThrownBy(() -> commandMemberService.update(updateMemberRequestDto))
                .isInstanceOf(MemberNotFoundException.class);
    }

    @Test
    @DisplayName("회원 정보 변경 성공")
    void updateSuccess() {
        //given
        Mockito.when(queryDslMemberRepository.findMemberByLoginId("loginId")).thenReturn(Optional.of(member));

        //when
        UpdateMemberResponseDto result = commandMemberService.update(new UpdateMemberRequestDto("loginId", "password"));

        //then
        verify(queryDslMemberRepository, atLeastOnce()).findMemberByLoginId("loginId");
    }

    @Test
    @DisplayName("회원 정보 삭제 시 해당 로그인 아이디가 존재 하지 않아 LoginIdExistsException 발생")
    void logicalDeleteThrowsLoginIdExistsException() {
        //given
        Mockito.when(queryDslMemberRepository.findMemberByLoginId("loginId")).thenReturn(Optional.empty());

        //when, then
        assertThatThrownBy(() -> commandMemberService.logicalDelete("loginId"))
                .isInstanceOf(LoginIdExistsException.class);
    }

    @Test
    @DisplayName("논리적 삭제 성공")
    void logicalDeleteSuccess() {
        //given
        Mockito.when(queryDslMemberRepository.findMemberByLoginId("loginId"))
                .thenReturn(Optional.of(member));

        //when
        commandMemberService.logicalDelete("loginId");

        //then
        verify(queryDslMemberRepository, atLeastOnce()).findMemberByLoginId("loginId");
    }

    @Test
    @DisplayName("로그인 아이디 중복 여부 확인, 중복된 로그인아이디가 존재할 경우 true 반환")
    void checkLoginIdReturnTrue() {
        //given
        Mockito.when(queryDslMemberRepository.existsLoginId("loginId")).thenReturn(true);

        //when
        CheckLoginIdResponseDto result = commandMemberService.checkLoginId("loginId");

        //then
        assertThat(result.isCheck()).isTrue();
        verify(queryDslMemberRepository, atLeastOnce()).existsLoginId("loginId");
    }

    @Test
    @DisplayName("로그인 아이디 중복 여부 확인, 중복된 로그인아이디가 존재하지 않을 경우 false 반환")
    void checkLoginIdReturnFalse() {
        //given
        Mockito.when(queryDslMemberRepository.existsLoginId("loginId")).thenReturn(false);

        //when
        CheckLoginIdResponseDto result = commandMemberService.checkLoginId("loginId");

        //then
        assertThat(result.isCheck()).isFalse();
        verify(queryDslMemberRepository, atLeastOnce()).existsLoginId("loginId");
    }
}
