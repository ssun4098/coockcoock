package com.coockcoock.shop.member.service.impl;

import com.coockcoock.shop.member.domain.Grade;
import com.coockcoock.shop.member.domain.Member;
import com.coockcoock.shop.member.dto.*;
import com.coockcoock.shop.member.exception.LoginIdExistsException;
import com.coockcoock.shop.member.exception.MemberNotFoundException;
import com.coockcoock.shop.member.repository.CommonMemberRepository;
import com.coockcoock.shop.member.repository.impl.QueryDslMemberRepository;
import com.coockcoock.shop.member.service.CommandMemberService;
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
    private final QueryDslMemberRepository queryDslMemberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public CreateMemberResponseDto create(CreateMemberRequestDto requestDto) {
        if(queryDslMemberRepository.existsLoginId(requestDto.getLoginId())) {
            throw new LoginIdExistsException(requestDto.getLoginId());
        }
        LocalDate signUpDate = LocalDate.now();
        commonMemberRepository.save(
                Member.builder()
                        .loginId(requestDto.getLoginId())
                        .password(passwordEncoder.encode(requestDto.getPassword()))
                        .signUpDate(signUpDate)
                        .grade(new Grade(0L, "default")).build());
        return new CreateMemberResponseDto(LocalDate.now());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public UpdateMemberResponseDto update(UpdateMemberRequestDto requestDto)  {
        Member member = queryDslMemberRepository.findMemberByLoginId(requestDto.getLoginId())
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
        Member member = queryDslMemberRepository
                .findMemberByLoginId(loginId).orElseThrow(() -> new LoginIdExistsException(loginId));
        member.logicDelete();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CheckLoginIdResponseDto checkLoginId(String loginId) {
        return new CheckLoginIdResponseDto(queryDslMemberRepository.existsLoginId(loginId));
    }
}
