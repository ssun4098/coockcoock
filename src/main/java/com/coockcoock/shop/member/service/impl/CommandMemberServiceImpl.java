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
import com.coockcoock.shop.utils.CookieUtil;
import com.coockcoock.shop.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

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
    private final RedisTemplate<String, String> redisTemplate;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final CookieUtil cookieUtil;
    @Value("${jwt.refreshKey}")
    private String refreshTokenKey;
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public CreateMemberResponseDto create(CreateMemberRequestDto requestDto) {
        if(queryMemberRepository.existsLoginId(requestDto.getLoginId())) {
            throw new LoginIdExistsException(requestDto.getLoginId());
        }
        commonMemberRepository.save(
                Member.builder()
                        .loginId(requestDto.getLoginId())
                        .password(passwordEncoder.encode(requestDto.getPassword()))
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
    public LoginResponseDto login(LoginRequestDto requestDto) {
        Member member = queryMemberRepository.findMemberByLoginId(requestDto.getLoginId())
                .orElseThrow(() -> new MemberNotFoundException("LoginId: " + requestDto.getLoginId()));
        passwordCheck(member, requestDto.getPassword());
        redisTemplate.expire(requestDto.getLoginId() + " " + refreshTokenKey, 6, TimeUnit.HOURS);
        Long exp = System.currentTimeMillis() + 1000L * 60 * 60;
        return new LoginResponseDto(cookieUtil.createCookie("token", jwtUtil.creatJwt(member, exp), exp));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void logout(String loginId, String token) {
        redisTemplate.delete(loginId + " " + refreshTokenKey);
        redisTemplate.opsForValue().set(token, loginId);
        redisTemplate.expire(token, 6, TimeUnit.HOURS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkBlackList(String token) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(token));
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
