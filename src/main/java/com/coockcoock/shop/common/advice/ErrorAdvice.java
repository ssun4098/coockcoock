package com.coockcoock.shop.common.advice;

import com.coockcoock.shop.common.dto.CommonErrorDto;
import com.coockcoock.shop.common.dto.CommonResponseDto;
import com.coockcoock.shop.member.exception.LoginTokenBlackListExistsException;
import com.coockcoock.shop.member.exception.LoginIdExistsException;
import com.coockcoock.shop.member.exception.MemberNotFoundException;
import com.coockcoock.shop.member.exception.PasswordNotMatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 예외 처리 Advice
 */
@RequiredArgsConstructor
@RestControllerAdvice
public class ErrorAdvice {

    /**
     * 404 Error Handler 메서드
     *
     * @param e 에러 정보가 담긴 객체
     * @return Http Status: 404 && ErrorMessage
     */
    @ExceptionHandler(value = {MemberNotFoundException.class})
    public CommonResponseDto<?> notFound(Exception e) {
        return CommonResponseDto.builder()
                .success(false)
                .errorDto(CommonErrorDto.builder()
                        .status(404)
                        .message(e.getMessage())
                        .build())
                .build();
    }

    @ExceptionHandler(value = {LoginIdExistsException.class, PasswordNotMatchException.class, LoginTokenBlackListExistsException.class})
    public CommonResponseDto<?> badRequest(Exception e) {
        return CommonResponseDto.builder()
                .success(false)
                .errorDto(CommonErrorDto.builder()
                        .status(400)
                        .message(e.getMessage())
                        .build())
                .build();
    }
}
