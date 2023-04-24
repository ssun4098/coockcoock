package com.coockcoock.shop.member.controller;

import com.coockcoock.shop.member.dto.*;
import com.coockcoock.shop.member.service.CommandMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 회원 관련 Rest Controller
 *
 * @since 23-04-24
 * @version 1.0
 */
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
    public ResponseEntity<CheckLoginIdResponseDto> checkLoginId(@Valid CheckLoginIdRequestDto requestDto) {
        return new ResponseEntity<>(commandMemberService.checkLoginId(requestDto.getLoginId()), HttpStatus.OK);
    }

    /**
     * 회원가입 메서드
     *
     * @param requestDto 회원가입 요청을 위한 DTO
     * @return 등록 완료 후 완료 시간 정보 반환
     * @since 24-04-24
     */
    @PostMapping
    public ResponseEntity<CreateMemberResponseDto> createMember(@Valid @RequestBody CreateMemberRequestDto requestDto) {
        return new ResponseEntity<>(commandMemberService.create(requestDto), HttpStatus.CREATED);
    }

    /**
     * 회원 정보 수정 메서드
     *
     * @param requestDto 수정할 회원의 정보
     * @return 수정 완료 후 완료 시간 정보 반환
     * @since 24-04-24
     */
    @PutMapping
    public ResponseEntity<UpdateMemberResponseDto> updateMEmber(@Valid @RequestBody UpdateMemberRequestDto requestDto) {
        return new ResponseEntity<>(commandMemberService.update(requestDto), HttpStatus.OK);
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
}
