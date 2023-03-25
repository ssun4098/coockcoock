package com.coockcoock.shop.member.exception;

/**
 * 회원가입 시 로그인 아이디 중복 Exception
 *
 * @version 1.0
 * @since 23-03-25
 */
public class LoginIdExistsException extends RuntimeException {
    public LoginIdExistsException(String loginId) {
        super(loginId + "is Exists");
    }
}
