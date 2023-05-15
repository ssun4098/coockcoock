package com.coockcoock.shop.filter;

import com.coockcoock.shop.member.exception.LoginException;
import com.coockcoock.shop.member.repository.CommonMemberRepository;
import com.coockcoock.shop.member.repository.QueryMemberRepository;
import com.coockcoock.shop.member.service.CommandMemberService;
import com.coockcoock.shop.utils.CookieUtil;
import com.coockcoock.shop.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
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
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;
    private final RedisTemplate<String, String> redisTemplate;
    private final QueryMemberRepository queryMemberRepository;
    private final JwtUtil jwtUtil;
    private final CookieUtil cookieUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("JwtFilter token: {}", token);
        if(Objects.nonNull(token) && jwtUtil.validToken(token)) {
            String loginId = jwtUtil.getLoginId(token.split(" ")[1].trim());

            if(Boolean.TRUE.equals(redisTemplate.hasKey(loginId + " Access Token"))) {
                throw new LoginException();
            }
            if(jwtUtil.isExpired(token) && Objects.nonNull(redisTemplate.opsForValue().getAndDelete(loginId+ " Refresh Token"))) {
                response.addCookie(cookieUtil.createCookie("AccessToken", jwtUtil.creatJwt(queryMemberRepository.findMemberByLoginId(loginId).get())));
            }
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginId);
            Authentication usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}
