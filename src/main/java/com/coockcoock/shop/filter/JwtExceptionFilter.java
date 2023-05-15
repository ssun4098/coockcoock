package com.coockcoock.shop.filter;


import com.coockcoock.shop.member.exception.LoginException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Jwt 필터 Exception 처리 필터
 */
@RequiredArgsConstructor
@Component
public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
          filterChain.doFilter(request, response);
        } catch (LoginException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().write("Please Re Login.");
        }
    }
}
