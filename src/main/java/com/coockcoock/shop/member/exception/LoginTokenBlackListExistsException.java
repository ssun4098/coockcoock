package com.coockcoock.shop.member.exception;

/**
 * 해당 Token이 블랙리스트에 포함되어 있는 경우 발생하는 Exception
 */
public class LoginTokenBlackListExistsException extends RuntimeException{
    public LoginTokenBlackListExistsException(String message) {
        super(message);
    }
}
