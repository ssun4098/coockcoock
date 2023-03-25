package com.coockcoock.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security Config Class
 *
 * @version 1.0
 * @since 23-03-25
 */
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) -> {
            auth.antMatchers("/h2-console/**").permitAll();
        });

        http.csrf().disable();
        http.headers().frameOptions().disable();

        http.formLogin();


        return http.build();
    }

    /**
     * 패스워드를 암호화 해주는 PasswordEncoder 객체를 SpringBean으로 등록
     *
     * @return PasswordEncoder 객체
     * @since 23-03-25
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
