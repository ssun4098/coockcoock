package com.coockcoock.shop.member.controller;

import com.coockcoock.shop.common.dto.CommonResponseDto;
import com.coockcoock.shop.member.dto.*;
import com.coockcoock.shop.member.service.CommandMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 회원 관련 Rest Controller
 *
 * @since 23-04-24
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/members")
public class CommandMemberController {
    private final CommandMemberService commandMemberService;

    /**
     * 회원 로그인 아이디 중복 여부 확인 메서드
     *
     * @param requestDto 확인할 로그인 아이디
     * @return 중복 결과
     * @since 24-04-24
     */
    @GetMapping("/duplicability")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponseDto<CheckLoginIdResponseDto> checkLoginId(@Valid CheckLoginIdRequestDto requestDto) {
        return CommonResponseDto.<CheckLoginIdResponseDto>builder()
                .success(true)
                .data(commandMemberService.checkLoginId(requestDto.getLoginId()))
                .build();
    }

    /**
     * 회원가입 메서드
     *
     * @param requestDto 회원가입 요청을 위한 DTO
     * @return 등록 완료 후 완료 시간 정보 반환
     * @since 24-04-24
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponseDto<CreateMemberResponseDto> createMember(@Valid @RequestBody CreateMemberRequestDto requestDto) {
        return CommonResponseDto.<CreateMemberResponseDto>builder()
                .success(true)
                .data(commandMemberService.create(requestDto))
                .build();
    }

    /**
     * 회원 정보 수정 메서드
     *
     * @param requestDto 수정할 회원의 정보
     * @return 수정 완료 후 완료 시간 정보 반환
     * @since 24-04-24
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CommonResponseDto<UpdateMemberResponseDto> updateMEmber(@Valid @RequestBody UpdateMemberRequestDto requestDto) {
        return CommonResponseDto.<UpdateMemberResponseDto>builder()
                .success(true)
                .data(commandMemberService.update(requestDto))
                .build();
    }

    /**
     * 회원 삭제 메서드
     *
     * @param requestDto 삭제할 회원의 loginId DTO
     * @since 24-04-24
     */
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logicDelete(@Valid DeleteRequestDto requestDto) {
        commandMemberService.logicalDelete(requestDto.getLoginId());
    }

    /**
     * 로그인 메서드 JWT AccessToken을 Cookie에 담아서 전달
     *
     * @param requestDto 로그인 요청 DTO
     * @since 24-05-15
     */
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponseDto<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto requestDto) {
        return CommonResponseDto.<LoginResponseDto>builder()
                .success(true)
                .data(commandMemberService.login(requestDto))
                .build();
    }

    @DeleteMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@RequestParam String loginId, HttpServletRequest request) {
        log.info("logout member: {}", loginId);
        commandMemberService.logout(loginId, request.getHeader(HttpHeaders.AUTHORIZATION));
    }
}
