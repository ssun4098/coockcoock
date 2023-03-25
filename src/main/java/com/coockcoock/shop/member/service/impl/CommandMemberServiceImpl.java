package com.coockcoock.shop.member.service.impl;

import com.coockcoock.shop.member.domain.Grade;
import com.coockcoock.shop.member.domain.Member;
import com.coockcoock.shop.member.dto.CreateMemberRequestDto;
import com.coockcoock.shop.member.dto.CreateMemberResponseDto;
import com.coockcoock.shop.member.dto.UpdateMemberRequestDto;
import com.coockcoock.shop.member.dto.UpdateMemberResponseDto;
import com.coockcoock.shop.member.repository.CommonMemberRepository;
import com.coockcoock.shop.member.repository.impl.QueryDslMemberRepository;
import com.coockcoock.shop.member.service.CommandMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
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
    public CreateMemberResponseDto create(CreateMemberRequestDto requestDto) throws LoginException {
        if(queryDslMemberRepository.existsLoginId(requestDto.getLoginId())) {
            throw new LoginException(requestDto.getLoginId());
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
    public UpdateMemberResponseDto update(UpdateMemberRequestDto requestDto) throws LoginException {
        if(queryDslMemberRepository.existsLoginId(requestDto.getLoginId())) {
            throw new LoginException(requestDto.getLoginId());
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void logicalDelete(String loginId) {

    }
}
