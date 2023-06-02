package com.coockcoock.shop.filter;

import com.coockcoock.shop.member.exception.JwtExpiredException;
import com.coockcoock.shop.member.exception.LoginTokenBlackListExistsException;
import com.coockcoock.shop.member.service.CommandMemberService;
import com.coockcoock.shop.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


/**
 * JWT 관련 Spring Security Filter
 *
 */
@Slf4j
@RequiredArgsConstructor
@ConditionalOnBean(JwtUtil.class)
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(Objects.nonNull(token) && jwtUtil.validToken(token)) {
            log.info("JwtFilter token: {}", token);

            checkExpired(token);

            String loginId = jwtUtil.getLoginId(token.split(" ")[1].trim());

            UserDetails userDetails = userDetailsService.loadUserByUsername(loginId);

            Authentication usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 해당 토큰의 유효기간을 확인합니다. 기간이 지났으면 JwtExpiredException 발생
     *
     * @param token 확인할 Token
     */
    private void checkExpired(String token) {
        if(jwtUtil.isExpired(token)) {
            throw new JwtExpiredException("Token Expired: " + token);
        }
    }
}
