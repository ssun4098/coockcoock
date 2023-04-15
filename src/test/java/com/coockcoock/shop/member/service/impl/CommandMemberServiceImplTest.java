package com.coockcoock.shop.member.service.impl;

import com.coockcoock.shop.member.repository.CommonMemberRepository;
import com.coockcoock.shop.member.repository.impl.QueryDslMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

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
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        commonMemberRepository = Mockito.mock(CommonMemberRepository.class);
        queryDslMemberRepository = Mockito.mock(QueryDslMemberRepository.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        commandMemberService = new CommandMemberServiceImpl(commonMemberRepository, queryDslMemberRepository, passwordEncoder);
    }

    
}