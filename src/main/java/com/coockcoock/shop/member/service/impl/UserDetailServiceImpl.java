package com.coockcoock.shop.member.service.impl;

import com.coockcoock.shop.member.domain.CustomUserDetail;
import com.coockcoock.shop.member.domain.Member;
import com.coockcoock.shop.member.exception.MemberNotFoundException;
import com.coockcoock.shop.member.repository.CommonMemberRepository;
import com.coockcoock.shop.member.repository.QueryMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 인증 관련 서비스 구현체
 *
 * @since 23-04-28
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final QueryMemberRepository queryMemberRepository;

    /**
     * 입력한 loginId를 가진 회원을 찾는 메서드
     *
     * @param loginId 입력한 loginId
     * @return SecurityContext에 넣을 UserDetail
     * @throws UsernameNotFoundException 없는 회원을 경우 NotFoundException
     * @since 23-04-28
     */
    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        log.info("loginId: {}", loginId);
        Member member = queryMemberRepository.findMemberByLoginId(loginId)
                .orElseThrow(() -> new MemberNotFoundException("LoginId: " + loginId));
        return new CustomUserDetail(member);
    }
}
