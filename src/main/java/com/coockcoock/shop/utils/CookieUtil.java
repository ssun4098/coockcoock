package com.coockcoock.shop.utils;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.util.Arrays;

@Component
public class CookieUtil {

    /**
     * 쿠키 생성
     *
     * @param name 생성할 쿠키의 이름
     * @param content 쿠키에 담을 내용
     * @return 생성한 쿠키
     */
    public Cookie createCookie(String name, String content, Long expire) {
        Cookie cookie = new Cookie(name, content);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/**");
        cookie.setMaxAge(expire.intValue());
        return cookie;
    }

    /**
     * 원하는 쿠키를 찾는 메서드
     *
     * @param cookies 쿠키가 담겨있는 배열
     * @param name 원하는 쿠키의 이름
     * @return 찾는 쿠키
     */
    public Cookie findCookie(Cookie[] cookies, String name) {
        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(""));
    }
}

