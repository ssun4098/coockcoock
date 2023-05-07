package com.coockcoock.shop.member.service.impl;

import com.coockcoock.shop.member.domain.Grade;
import com.coockcoock.shop.member.domain.Member;
import com.coockcoock.shop.member.dto.*;
import com.coockcoock.shop.member.exception.LoginIdExistsException;
import com.coockcoock.shop.member.exception.MemberNotFoundException;
import com.coockcoock.shop.member.exception.PasswordNotMatchException;
import com.coockcoock.shop.member.repository.CommonMemberRepository;
import com.coockcoock.shop.member.repository.QueryMemberRepository;
import com.coockcoock.shop.member.service.CommandMemberService;
import com.coockcoock.shop.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 *  * 회원 생성, 업데이터, 삭제 Service 구현체
 *
 * @version 1.0
 * @since 23-03-25
 */
@RequiredArgsConstructor
@Service
public class CommandMemberServiceImpl implements CommandMemberService{
    private final CommonMemberRepository commonMemberRepository;
    private final QueryMemberRepository queryMemberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public CreateMemberResponseDto create(CreateMemberRequestDto requestDto) {
        if(queryMemberRepository.existsLoginId(requestDto.getLoginId())) {
            throw new LoginIdExistsException(requestDto.getLoginId());
        }
        LocalDate signUpDate = LocalDate.now();
        commonMemberRepository.save(
                Member.builder()
                        .loginId(requestDto.getLoginId())
                        .password(passwordEncoder.encode(requestDto.getPassword()))
                        .signUpDate(signUpDate)
                        .grade(new Grade(1L, "DEFAULT")).build());
        return new CreateMemberResponseDto(LocalDate.now());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public UpdateMemberResponseDto update(UpdateMemberRequestDto requestDto)  {
        Member member = queryMemberRepository.findMemberByLoginId(requestDto.getLoginId())
                .orElseThrow(() -> new MemberNotFoundException("LoginId: " + requestDto.getLoginId()));

        member.changePassword(passwordEncoder.encode(requestDto.getPassword()));
        return new UpdateMemberResponseDto(LocalDate.now());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void logicalDelete(String loginId) {
        Member member = queryMemberRepository
                .findMemberByLoginId(loginId).orElseThrow(() -> new LoginIdExistsException(loginId));
        member.logicDelete();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CheckLoginIdResponseDto checkLoginId(String loginId) {
        return new CheckLoginIdResponseDto(queryMemberRepository.existsLoginId(loginId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String login(LoginRequestDto requestDto) {
        Member member = queryMemberRepository.findMemberByLoginId(requestDto.getLoginId())
                .orElseThrow(() -> new MemberNotFoundException("LoginId: " + requestDto.getLoginId()));
        passwordCheck(member, requestDto.getPassword());
        return jwtUtil.creatJwt(member);
    }

    /**
     * 입력한 패스워드를 확인하는 메서드
     * 만약 올바르게 입력하지 않을 경우 PasswordNotMatchException 발생
     *
     * @param member   확인할 회원
     * @param password 입력한 비밀번호
     */
    private void passwordCheck(Member member, String password) {
        if(!passwordEncoder.matches(password, member.getPassword())) {
            throw new PasswordNotMatchException();
        }
    }
}
