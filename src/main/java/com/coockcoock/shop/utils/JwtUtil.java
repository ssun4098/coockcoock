package com.coockcoock.shop.utils;

import com.coockcoock.shop.member.domain.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * JWT 유틸 클래스
 *
 * @since 23-04-28
 */
@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secretKey}")
    private String secretKey;
    private final Long exp = 1000L * 60 * 60;

    /**
     *  JWT에서 로그인 아이디 추출 메서드
     *
     * @param token JWT
     * @return 추출한 loginId;
     *
     * @since 23-04-28
     */
    public String getLoginId(String token) {
        log.info("getLoginId of token: {}", token);
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().getSubject();
    }

    /**
     * JWT 생성 메서드
     *
     * @param member 추출할 member
     * @return JWT
     * @since 23-04-28
     */
    public String creatJwt(Member member) {
        Claims claims = Jwts.claims().setSubject(member.getLoginId());
        claims.put("grade", member.getGrade());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + exp))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validToke(String token) {
        if(!token.substring(0, "BEARER ".length()).equalsIgnoreCase("BEARER ")) {
            log.info("BEARER 파싱 실패");
            return false;
        }
        log.info("BEARER 파싱 성공");
        token = token.split(" ")[1].trim();
        log.info("만료기한 검사 결과: {}", isExpired(token));
        return !isExpired(token);
    }

    /**
     * 유효기간 확인
     *
     * @param token JWT
     * @return 만료시 True, 아니면 False
     * @since 23-04-28
     */
    public boolean isExpired(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token).getBody()
                .getExpiration()
                .before(new Date());
    }
}
