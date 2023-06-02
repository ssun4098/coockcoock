package com.coockcoock.shop.filter;


import com.coockcoock.shop.common.dto.CommonResponseDto;
import com.coockcoock.shop.member.exception.JwtExpiredException;
import com.coockcoock.shop.member.exception.LoginTokenBlackListExistsException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@Slf4j
@ConditionalOnBean(JwtFilter.class)
@RequiredArgsConstructor
@Component
public class JwtExceptionFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
          filterChain.doFilter(request, response);
        } catch (LoginTokenBlackListExistsException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setContentType(MediaType.APPLICATION_JSON.toString());
            CommonResponseDto<String> errorResponse = CommonResponseDto.<String>builder()
                    .success(false)
                    .data(e.getMessage())
                    .build();
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
            log.error("LoginTokenBlackListExistsException: {}", e.getMessage());
        } catch (JwtExpiredException e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON.toString());
            CommonResponseDto<String> errorResponse = CommonResponseDto.<String>builder()
                    .success(false)
                    .data(e.getMessage())
                    .build();
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
            log.error("JwtExpiredException: {}", e.getMessage());
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setContentType(MediaType.APPLICATION_JSON.toString());
            CommonResponseDto<String> errorResponse = CommonResponseDto.<String>builder()
                    .success(false)
                    .data(e.getMessage())
                    .build();
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
            log.error("Exception: {}", e.getMessage());
        }
    }
}
