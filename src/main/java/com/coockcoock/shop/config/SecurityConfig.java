package com.coockcoock.shop.config;

import com.coockcoock.shop.filter.JwtExceptionFilter;
import com.coockcoock.shop.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security Config Class
 *
 * @version 1.0
 * @since 23-03-25
 */
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    private final JwtFilter jwtFilter;
    private final JwtExceptionFilter jwtExceptionFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) -> {
            auth.antMatchers("/h2-console/**").permitAll();
            auth.antMatchers("/v1/members/**").permitAll();
           // auth.antMatchers("/v1/ingredients/**").hasAnyRole("default", "manager");
        });

        http.csrf().disable();
        http.cors().disable();
        http.headers().frameOptions().disable();
        http.formLogin().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtExceptionFilter, JwtFilter.class);

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
